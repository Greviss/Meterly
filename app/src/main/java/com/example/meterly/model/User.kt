package com.example.meterly.model

data class User(
    val uid: String = "",
    val fullName: String = "",
    val phoneNumber: String = "",
    val currentAddressId: String = "",
    val fcmToken: String = ""
)