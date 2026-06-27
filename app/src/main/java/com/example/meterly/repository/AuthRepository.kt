package com.example.meterly.repository

import android.app.Activity
import com.example.meterly.model.User
import com.example.meterly.state.AuthState
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit

class AuthRepository {
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    private var storedVerificationId: String? = null

    fun sendVerificationCode(
        phone: String,
        activity: Activity
    ) {
        Log.d("AUTH_DEBUG", "sendVerificationCode: $phone")
        _authState.value = AuthState.Loading

        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d("AUTH_DEBUG", "onVerificationCompleted")
                signInWithCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.e(
                    "AUTH_DEBUG",
                    "onVerificationFailed",
                    e
                )

                _authState.value =
                    AuthState.Error(
                        e.localizedMessage
                            ?: "Помилка верифікації"
                    )
            }

            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                Log.d(
                    "AUTH_DEBUG",
                    "onCodeSent"
                )

                storedVerificationId = verificationId
                _authState.value = AuthState.CodeSent(verificationId)
            }
        }

        val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phone)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(activity)
                .setCallbacks(callbacks)
                .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun verifyCode(code: String) {
        _authState.value = AuthState.Loading

        val verificationId = storedVerificationId ?: run {
            _authState.value =
                AuthState.Error("Сесія закінчилась, спробуйте ще раз")
            return
        }

        val credential = PhoneAuthProvider.getCredential(
            verificationId,
            code
        )

        signInWithCredential(credential)
    }

    private fun signInWithCredential(
        credential: PhoneAuthCredential
    ) {
        auth.signInWithCredential(credential)
            .addOnSuccessListener {
                _authState.value = AuthState.Success
            }
            .addOnFailureListener { e ->
                _authState.value =
                    AuthState.Error(
                        e.message ?: "Невірний код"
                    )
            }
    }

    suspend fun saveUserToFirestore(
        fullName: String,
        address: String,
        phone: String
    ) {
        val fcmToken = try {
            FirebaseMessaging.getInstance()
                .token
                .await()
        } catch (_: Exception) {
            ""
        }

        val docRef = firestore.collection("users").document(phone)
        val snapshot = docRef.get().await()

        if (!snapshot.exists()) {
            val user = User(
                uid = auth.currentUser?.uid ?: "",
                fullName = fullName,
                phoneNumber = phone,
                addresses = listOf(address),
                fcmToken = fcmToken
            )

            docRef.set(user).await()
        } else {
            val addresses = snapshot.get("addresses") as? List<String> ?: emptyList()

            if (addresses.none {
                    it.trim().equals(address.trim(), ignoreCase = true)
                }) {
                docRef.update(
                    "addresses",
                    addresses + address
                ).await()
            }

            docRef.update(
                "fcmToken",
                fcmToken
            ).await()
        }
    }

    suspend fun checkUserByAddressAndPhone(
        address: String,
        phone: String
    ): Boolean {

        val snapshot = firestore
            .collection("users")
            .document(phone)
            .get()
            .await()

        if (!snapshot.exists())
            return false

        val addresses =
            snapshot.get("addresses") as? List<String>
                ?: emptyList()

        return addresses.any {
            it.trim().equals(address.trim(), ignoreCase = true)
        }
    }

    suspend fun updateFcmToken() {
        val phone = auth.currentUser?.phoneNumber ?: return

        val token = FirebaseMessaging
            .getInstance()
            .token
            .await()

        firestore.collection("users")
            .document(phone)
            .update("fcmToken", token)
            .await()
    }

    fun resetState() {
        _authState.value = AuthState.Idle
    }

    fun observeAuthState(onChanged: (Boolean) -> Unit) {
        auth.addAuthStateListener { firebaseAuth ->
            onChanged(
                firebaseAuth.currentUser != null
            )
        }
    }

    fun getCurrentUser() = auth.currentUser

    fun signOut() {
        auth.signOut()
    }
}