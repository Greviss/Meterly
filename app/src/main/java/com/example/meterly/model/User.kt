package com.example.meterly.model

data class User(
    val uid: String = "",
    val fullName: String = "",
    val address: String = "",
    val phoneNumber: String = "",
    val fcmToken: String = ""
)