package com.example.meterly.model

data class ProfileState(
    val user: User? = null,
    val addresses: List<Address> = emptyList(),
    val currentAddress: Address? = null
)