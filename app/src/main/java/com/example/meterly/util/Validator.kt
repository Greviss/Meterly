package com.example.meterly.util

object Validator {
    fun isValidFullName(name: String) : Boolean {
        val regex = Regex("^[А-ЯЇІЄ][а-яїіє']+\\s[А-ЯЇІЄ][а-яїіє']+\\s[А-ЯЇІЄ][а-яїіє']+\$")
        return regex.matches(name.trim())
    }

    fun isValidAddress(address: String): Boolean {
        val trimmed = address.trim()

        if (trimmed.length < 8) {
            return false
        }

        return Regex("^(Вул\\.|вул\\.|Пр\\.|пр\\.|Пров\\.|пров\\.)\\s.+").matches(trimmed)
    }

    fun isValidPhone(phone: String) : Boolean {
        val cleaned = phone.replace(" ", "").replace("-", "")
        return cleaned.matches(Regex("^(\\+380|0)\\d{9}\$"))
    }

    fun normalizePhone(phone: String) : String {
        val cleaned = phone.replace(" ", "").replace("-", "")
        return if (cleaned.startsWith("0")) "+38$cleaned" else cleaned
    }
}