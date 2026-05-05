package com.example.csgocaseswatcherapp.features.caseanalytics.view.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CaseAnalyticsDailyModel(
    val dailyAvgReturnInPercent: String,
    val dailyAvgReturnInRUB: String,
    val dailyStandardDeviation: String,
    val dailySharpRatio: String,
) : Parcelable
