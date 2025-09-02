package com.example.csgocaseswatcherapp.features.sortingmodal.entities

enum class SortingMethod {
    ByName,
    ByAmount,
    ByPrice,
    ByOverallValue,
    ByProfitLoss
}
fun SortingMethod.toText(): String {
    return name
        .replace(Regex("([a-z])([A-Z])"), "$1 $2")
        .lowercase()
        .split(" ")
        .joinToString(" ") { it.replaceFirstChar(Char::titlecase) }
}
