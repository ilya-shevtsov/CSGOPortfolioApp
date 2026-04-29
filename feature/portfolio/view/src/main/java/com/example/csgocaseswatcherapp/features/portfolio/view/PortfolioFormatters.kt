package com.example.csgocaseswatcherapp.features.portfolio.view


import java.text.NumberFormat
import java.util.Locale
import kotlin.math.abs

private val usdFormatter: NumberFormat
    get() = NumberFormat.getCurrencyInstance(Locale.US)

fun formatUsd(value: Double): String {
    return usdFormatter.format(value)
}

fun formatSignedUsd(value: Double): String {
    val formatted = usdFormatter.format(abs(value))
    return if (value >= 0) "+$formatted" else "-$formatted"
}

fun formatSignedPercent(value: Double): String {
    val sign = if (value >= 0) "+" else ""
    return sign + String.format(Locale.US, "%.2f%%", value)
}