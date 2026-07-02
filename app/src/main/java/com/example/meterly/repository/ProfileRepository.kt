package com.example.meterly.repository

import com.example.meterly.model.Address
import com.example.meterly.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ProfileRepository {
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    suspend fun getCurrentUser(): User? {

        val phone = auth.currentUser?.phoneNumber
            ?: return null

        val snapshot = firestore
            .collection("users")
            .document(phone)
            .get()
            .await()

        return snapshot.toObject(User::class.java)
    }

    suspend fun getAddresses(): List<Address> {

        val phone = auth.currentUser?.phoneNumber
            ?: return emptyList()

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

        val phone = auth.currentUser?.phoneNumber
            ?: return null

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
}