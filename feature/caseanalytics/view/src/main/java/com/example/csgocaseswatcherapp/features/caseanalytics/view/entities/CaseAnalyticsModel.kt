package com.example.csgocaseswatcherapp.features.caseanalytics.view.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CaseAnalyticsModel(
    val caseName: String,
    val imageUrl: String,
    val dailyData: CaseAnalyticsDailyModel,
    val monthlyData: CaseAnalyticsMonthlyModel
) : Parcelable
