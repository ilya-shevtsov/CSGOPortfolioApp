package com.example.csgocaseswatcherapp.presentation.model.caseanalyticsitem

import android.os.Parcelable
import com.example.csgocaseswatcherapp.data.model.caseanalytics.CaseAnalyticsMapper
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
