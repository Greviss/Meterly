package com.example.meterly.model

enum class UtilityType {
    GAS, WATER, LIGHT, SEWERAGE
}

data class Payment(
    val id: String = "",
    val utilityType: String = "",
    val monthBegin: Double = 0.0,
    val monthEnd: Double = 0.0,
    val consumption: Double = 0.0,
    val rate: Double = 0.0,
    val amountDue: Double = 0.0,
    val isPaid: Boolean = false,
    val receiptUri: String = "",
    val receiptFileName: String = "",
    val date: Long = 0L,
    val month: Int = 0,
    val year: Int = 0,
    val addressId: String = ""
)
