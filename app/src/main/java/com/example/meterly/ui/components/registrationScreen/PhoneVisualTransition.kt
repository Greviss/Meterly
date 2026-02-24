package com.example.meterly.ui.components.registrationScreen

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val digits = text.text
        val length = digits.length
        var out = "+380 "

        for (i in digits.indices) {
            out += digits[i]
            if (i == 1 || i == 4 || i == 6) out += " "
        }

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                val clamped = offset.coerceIn(0, length)
                return when {
                    clamped <= 0 -> 5
                    clamped <= 2 -> 5 + clamped
                    clamped <= 5 -> 6 + clamped
                    clamped <= 7 -> 7 + clamped
                    clamped <= 9 -> 8 + clamped
                    else -> 17
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                val result = when {
                    offset <= 5 -> 0
                    offset <= 7 -> offset - 5
                    offset <= 11 -> offset - 6
                    offset <= 14 -> offset - 7
                    offset <= 17 -> offset - 8
                    else -> 9
                }
                return result.coerceIn(0, length)
            }
        }

        return TransformedText(AnnotatedString(out), offsetMapping)
    }
}