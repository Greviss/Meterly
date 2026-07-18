package com.example.meterly.util

object AmountFormatter {
    fun format(amount: Double, round: Boolean): String {
        return if (round) {
            "${amount.toInt()} грн."
        } else {
            String.format("%.2f", amount) + " грн."
        }
    }
}
