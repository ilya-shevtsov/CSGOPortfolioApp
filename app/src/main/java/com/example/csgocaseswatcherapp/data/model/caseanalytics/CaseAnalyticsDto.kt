package com.example.csgocaseswatcherapp.data.model.caseanalytics

import java.io.Serializable
import com.google.gson.annotations.SerializedName

data class CaseAnalyticsDto(

    //Unresolved Reference because library version, android studio version,
    //Gradle version or Kotlin version mismatch

    @SerializedName("name")
    val name: String,

    @SerializedName("dailyAvgReturnInPercent")
    val dailyAvgReturnInPercent: Double,

    @SerializedName("dailyAvgReturnInRUB")
    val dailyAvgReturnInRUB: Double,

    @SerializedName("dailyStandardDeviation")
    val dailyStandardDeviation: Double,

    @SerializedName("dailySharpRatio")
    val dailySharpRatio: Double,

    @SerializedName("monthlyAvgReturnInPercent")
    val monthlyAvgReturnInPercent: Double,

    @SerializedName("monthlyAvgReturnInRUB")
    val monthlyAvgReturnInRUB: Double,

    @SerializedName("monthlyStandardDeviation")
    val monthlyStandardDeviation: Double,

    @SerializedName("monthlySharpRatio")
    val monthlySharpRatio: Double
) : Serializable