package com.example.csgocaseswatcherapp.features.caseanalytics.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class CaseAnalyticsDto(

    //Unresolved Reference because library version, android studio version,
    //Gradle version or Kotlin version mismatch

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