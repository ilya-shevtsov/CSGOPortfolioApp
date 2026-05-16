package com.example.csgocaseswatcherapp.features.caseanalytics.view.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CaseAnalyticsDailyModel(
    val avgReturnInPercent: String,
    val avgReturnInRUB: String,
    val standardDeviation: String,
    val sharpRatio: String,
) : Parcelable
