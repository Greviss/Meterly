package com.example.meterly.repository

import android.app.Activity
import com.example.meterly.model.User
import com.example.meterly.state.AuthState
import com.google.firebase.Firebase
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
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
        _authState.value = AuthState.Loading

        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                signInWithCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                _authState.value = AuthState.Error(e.message ?: "Помилка верифікації")
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
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
        val uid = auth.currentUser?.uid ?: return
        val doc =
            firestore.collection("users")
                .document(uid)
                .get()
                .await()

        if (doc.exists()) return

        val fcmToken =
            try {
                FirebaseMessaging.getInstance()
                    .token
                    .await()
            } catch (_: Exception) {
                ""
            }

        val user = User(
            uid = uid,
            fullName = fullName,
            address = address,
            phoneNumber = phone,
            fcmToken = fcmToken
        )

        firestore.collection("users")
            .document(uid)
            .set(user)
            .await()
    }

    suspend fun checkUserExists(phone: String): Boolean {
        val snapshot = firestore.collection("users")
            .whereEqualTo(
                "phoneNumber",
                phone
            )
            .get()
            .await()

        return !snapshot.isEmpty
    }

    suspend fun checkUserByAddressAndPhone(
        address: String,
        phone: String
    ): Boolean {
        val snapshot = firestore.collection("users")
            .whereEqualTo(
                "address",
                address)
            .whereEqualTo(
                "phoneNumber",
                phone)
            .get()
            .await()

        return !snapshot.isEmpty
    }

    suspend fun checkFullNameExists(fullName: String): Boolean {
        val snapshot = firestore.collection("users")
            .whereEqualTo(
                    "fullName",
                    fullName
            )
            .get()
            .await()

        return !snapshot.isEmpty
    }

    suspend fun updateFcmToken() {
        val uid = auth.currentUser?.uid ?: return
        val token = FirebaseMessaging
            .getInstance()
            .token
            .await()

        firestore
            .collection("users")
            .document(uid)
            .update(
                "fcmToken",
                token
            )
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