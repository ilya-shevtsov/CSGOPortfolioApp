package com.example.csgocaseswatcherapp.features.caseanalytics.view.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CaseAnalyticsMonthlyModel(
    val avgReturnInPercent: String,
    val avgReturnInRUB: String,
    val standardDeviation: String,
    val sharpRatio: String,
) : Parcelable