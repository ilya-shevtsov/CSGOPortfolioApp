package com.example.csgocaseswatcherapp.features.caseanalytics.view.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CaseAnalyticsModel(
    val caseName: String,
    val dailyAvgReturnInPercent: String,
    val dailyAvgReturnInRUB: String,
    val dailyStandardDeviation: String,
    val dailySharpRatio: String,
    val monthlyAvgReturnInPercent: String,
    val monthlyAvgReturnInRUB: String,
    val monthlyStandardDeviation: String,
    val monthlySharpRatio: String,
    val imageUrl: String
) : Parcelable
