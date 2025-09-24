package com.example.csgocaseswatcherapp.features.caseanalytics.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class CaseAnalyticsDto(
    val name: String,
    val dailyAvgReturnInPercent: Double,
    val dailyAvgReturnInRUB: Double,
    val dailyStandardDeviation: Double,
    val dailySharpRatio: Double,
    val monthlyAvgReturnInPercent: Double,
    val monthlyAvgReturnInRUB: Double,
    val monthlyStandardDeviation: Double,
    val monthlySharpRatio: Double
)