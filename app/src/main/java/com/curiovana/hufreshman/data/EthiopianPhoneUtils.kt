package com.curiovana.hufreshman.data

/**
 * Utility for formatting and validating Ethiopian phone numbers.
 * Supported mobile formats:
 * - Ethio Telecom: starts with 09 (e.g. 09xxxxxxxx, 10 digits)
 * - Safaricom Ethiopia: starts with 07 (e.g. 07xxxxxxxx, 10 digits)
 */
object EthiopianPhoneUtils {

    /**
     * Formats raw phone input to Ethiopian standard:
     * - Strips non-digits
     * - Converts +251... / 251... prefix to 0...
     * - Automatically puts '0' in front if the user starts typing without it (e.g. 9... -> 09..., 7... -> 07...)
     * - Limits to 10 digits (09xxxxxxxx or 07xxxxxxxx)
     */
    fun formatInput(input: String): String {
        // Strip non-digits
        var digits = input.filter { it.isDigit() }
        if (digits.isEmpty()) return ""

        // Handle Ethiopian country code 251
        if (digits.startsWith("251")) {
            digits = digits.removePrefix("251")
            digits = "0$digits"
        } else if (digits.startsWith("9") || digits.startsWith("7")) {
            // Automatically put 0 in front
            digits = "0$digits"
        } else if (!digits.startsWith("0")) {
            // Automatically put 0 in front
            digits = "0$digits"
        }

        // Ethiopian mobile numbers are exactly 10 digits
        return if (digits.length > 10) digits.take(10) else digits
    }

    /**
     * Validates that the phone number is a valid Ethiopian mobile number:
     * Exactly 10 digits and starts with 09 (Ethio Telecom) or 07 (Safaricom).
     */
    fun isValid(phone: String): Boolean {
        val clean = phone.filter { it.isDigit() }
        val normalized = when {
            clean.startsWith("251") -> "0" + clean.removePrefix("251")
            clean.startsWith("9") || clean.startsWith("7") -> "0$clean"
            else -> clean
        }
        return (normalized.startsWith("09") || normalized.startsWith("07")) && normalized.length == 10
    }

    /**
     * Returns a human-friendly error message if invalid, or null if valid.
     */
    fun getValidationError(phone: String): String? {
        val clean = phone.filter { it.isDigit() }
        if (clean.isBlank() || clean == "0") {
            return "Please enter your phone number."
        }
        val normalized = when {
            clean.startsWith("251") -> "0" + clean.removePrefix("251")
            clean.startsWith("9") || clean.startsWith("7") -> "0$clean"
            else -> clean
        }
        if (!normalized.startsWith("09") && !normalized.startsWith("07")) {
            return "Only Ethiopian phone numbers starting with 09 (Ethio Telecom) or 07 (Safaricom) are supported."
        }
        if (normalized.length < 10) {
            return "Please enter your complete phone number."
        }
        if (normalized.length > 10) {
            return "Please enter a valid phone number."
        }
        return null
    }

    /**
     * Formats phone number for display with spaces, e.g. "0912 34 56 78"
     */
    fun formatDisplay(phone: String): String {
        val digits = phone.filter { it.isDigit() }
        return when {
            digits.length == 10 -> "${digits.substring(0, 4)} ${digits.substring(4, 6)} ${digits.substring(6, 8)} ${digits.substring(8, 10)}"
            else -> phone
        }
    }
}
