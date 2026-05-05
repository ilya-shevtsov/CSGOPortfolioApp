package com.example.csgocaseswatcherapp.features.caseanalytics.view.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CaseAnalyticsMonthlyModel(
    val monthlyAvgReturnInPercent: String,
    val monthlyAvgReturnInRUB: String,
    val monthlyStandardDeviation: String,
    val monthlySharpRatio: String,
) : Parcelable