package com.example.csgocaseswatcherapp.presentation.screens.caseanalytics.view.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CaseAnalyticsModel(
    val caseName: String,
    val dailyAvgReturnInPercent: Double,
    val dailyAvgReturnInRUB: Double,
    val dailyStandardDeviation: Double,
    val dailySharpRatio: Double,
    val monthlyAvgReturnInPercent: Double,
    val monthlyAvgReturnInRUB: Double,
    val monthlyStandardDeviation: Double,
    val monthlySharpRatio: Double,
    val imageUrl: String
) : Parcelable
