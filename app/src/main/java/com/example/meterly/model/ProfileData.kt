package com.example.meterly.model

data class ProfileData(
    val user: User?,
    val addresses: List<Address>,
    val currentAddress: Address?
)
