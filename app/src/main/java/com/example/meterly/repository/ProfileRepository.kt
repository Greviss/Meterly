package com.example.meterly.repository

import com.example.meterly.model.Address
import com.example.meterly.model.ProfileData
import com.example.meterly.model.ProfileState
import com.example.meterly.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.tasks.await

class ProfileRepository {
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()
    private var profileListener: ListenerRegistration? = null

    fun observeProfile(
        onChanged: (ProfileState) -> Unit
    ) {
        val phone = auth.currentUser?.phoneNumber ?: return

        profileListener?.remove()

        profileListener = firestore
            .collection("users")
            .document(phone)
            .addSnapshotListener { snapshot, _ ->
                if (snapshot == null || !snapshot.exists())
                    return@addSnapshotListener

                val user = snapshot.toObject(User::class.java)

                firestore.collection("users")
                    .document(phone)
                    .collection("addresses")
                    .get()
                    .addOnSuccessListener { result ->

                        val addresses = result.documents.map {

                            Address(
                                id = it.id,
                                address = it.getString("address") ?: "",
                                createdAt = it.getLong("createdAt") ?: 0L
                            )

                        }

                        val current = addresses.firstOrNull {
                            it.id == user?.currentAddressId
                        }

                        onChanged(
                            ProfileState(
                                user = user,
                                addresses = addresses,
                                currentAddress = current
                            )
                        )
                    }
            }
    }

    fun removeListener(){
        profileListener?.remove()
    }

    suspend fun addAddress(address: String) {
        val phone = auth.currentUser?.phoneNumber ?: return

        val addressesRef = firestore
            .collection("users")
            .document(phone)
            .collection("addresses")

        val exists = addressesRef
            .whereEqualTo("address", address.trim())
            .get()
            .await()

        if (!exists.isEmpty) return

        val newRef = addressesRef.document()

        val newAddress = Address(
            id = newRef.id,
            address = address.trim(),
            createdAt = System.currentTimeMillis()
        )

        newRef.set(newAddress).await()

        firestore.collection("users")
            .document(phone)
            .update(
                "currentAddressId",
                newRef.id
            )
            .await()
    }

    suspend fun deleteAddress(addressId: String) {
        val phone = auth.currentUser?.phoneNumber ?: return

        val userRef = firestore
            .collection("users")
            .document(phone)

        val addressesRef = userRef.collection("addresses")

        addressesRef
            .document(addressId)
            .delete()
            .await()

        val user = getCurrentUser() ?: return

        if (user.currentAddressId == addressId) {
            val remaining = addressesRef
                .get()
                .await()

            if (remaining.documents.isNotEmpty()) {
                userRef.update(
                    "currentAddressId",
                    remaining.documents.first().id
                ).await()
            } else {
                userRef.update(
                    "currentAddressId",
                    ""
                ).await()
            }
        }
    }

    suspend fun updateAddress(
        addressId: String,
        newAddress: String
    ) {
        val phone = auth.currentUser?.phoneNumber ?: return

        firestore.collection("users")
            .document(phone)
            .collection("addresses")
            .document(addressId)
            .update(
                "address",
                newAddress
            )
            .await()
    }

    suspend fun setCurrentAddress(addressId: String) {
        val phone = auth.currentUser?.phoneNumber ?: return

        firestore.collection("users")
            .document(phone)
            .update(
                "currentAddressId",
                addressId
            )
            .await()
    }

    suspend fun getProfileData(): ProfileData {
        val phone = auth.currentUser?.phoneNumber ?: return ProfileData(
                user = null,
                addresses = emptyList(),
                currentAddress = null
            )

        val userSnapshot = firestore
            .collection("users")
            .document(phone)
            .get()
            .await()

        val user = userSnapshot.toObject(User::class.java)

        val addressSnapshots = firestore
            .collection("users")
            .document(phone)
            .collection("addresses")
            .get()
            .await()

        val addresses = addressSnapshots.documents.map {

            Address(
                id = it.id,
                address = it.getString("address") ?: "",
                createdAt = it.getLong("createdAt") ?: 0L
            )

        }

        val currentAddress = addresses.firstOrNull {
            it.id == user?.currentAddressId
        }

        return ProfileData(
            user = user,
            addresses = addresses,
            currentAddress = currentAddress
        )
    }

    suspend fun getCurrentUser(): User? {
        val phone = auth.currentUser?.phoneNumber ?: return null

        val snapshot = firestore
            .collection("users")
            .document(phone)
            .get()
            .await()

        return snapshot.toObject(User::class.java)
    }

    suspend fun getAddresses(): List<Address> {
        val phone = auth.currentUser?.phoneNumber ?: return emptyList()

        val snapshot = firestore
            .collection("users")
            .document(phone)
            .collection("addresses")
            .get()
            .await()

        return snapshot.documents.map {

            Address(
                id = it.id,
                address = it.getString("address") ?: "",
                createdAt = it.getLong("createdAt") ?: 0L
            )
        }
    }

    suspend fun getCurrentAddress(): Address? {
        val user = getCurrentUser() ?: return null

        if (user.currentAddressId.isBlank())
            return null

        val phone = auth.currentUser?.phoneNumber ?: return null

        val snapshot = firestore
            .collection("users")
            .document(phone)
            .collection("addresses")
            .document(user.currentAddressId)
            .get()
            .await()

        if (!snapshot.exists())
            return null

        return Address(
            id = snapshot.id,
            address = snapshot.getString("address") ?: "",
            createdAt = snapshot.getLong("createdAt") ?: 0L
        )
    }

    suspend fun updateFullName(fullName: String){
        val phone = auth.currentUser?.phoneNumber ?: return

        firestore.collection("users")
            .document(phone)
            .update("fullName", fullName)
            .await()
    }

    suspend fun deleteAccount() {
        val user = auth.currentUser ?: return
        val phone = user.phoneNumber ?: return

        val addressesRef = firestore
            .collection("users")
            .document(phone)
            .collection("addresses")

        addressesRef
            .get()
            .await()
            .documents
            .forEach {

                it.reference.delete().await()

            }

        firestore
            .collection("users")
            .document(phone)
            .delete()
            .await()

        user.delete().await()
        auth.signOut()
    }
}