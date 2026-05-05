package com.example.csgocaseswatcherapp.features.caseanalytics.view.entities

import com.example.csgocaseswatcherapp.features.caseanalytics.domain.entities.CaseAnalytics
import kotlin.math.pow
import kotlin.math.round

object CaseAnalyticsItemMapper {

    fun map(caseAnalytics: CaseAnalytics): CaseAnalyticsModel {
        return CaseAnalyticsModel(
            caseName = caseAnalytics.caseName,
            imageUrl = getCaseImage(caseAnalytics.caseName),
            dailyData = CaseAnalyticsDailyModel(
                avgReturnInPercent = "${caseAnalytics.dailyAvgReturnInPercent} %",
                avgReturnInRUB = caseAnalytics.dailyAvgReturnInRUB.toString(),
                standardDeviation = caseAnalytics.dailyStandardDeviation.toDoubleWith5Decimals().toString(),
                sharpRatio = caseAnalytics.dailySharpRatio.toDoubleWith5Decimals().toString(),
            ),
            monthlyData = CaseAnalyticsMonthlyModel(
                avgReturnInPercent = "${caseAnalytics.monthlyAvgReturnInPercent} %",
                avgReturnInRUB = caseAnalytics.monthlyAvgReturnInRUB.toString(),
                standardDeviation = caseAnalytics.monthlyStandardDeviation.toDoubleWith5Decimals().toString(),
                sharpRatio = caseAnalytics.monthlySharpRatio.toDoubleWith5Decimals().toString(),
            )
        )
    }

    private fun getCaseImage(caseName: String): String {
        val newName = caseName
            .replace(" ", "%20")
            .replace(":", "%3A")
            .replace("&", "%26")
        return "https://api.steamapis.com/image/item/730/$newName"
    }

    private fun Double.toDoubleWith5Decimals(): Double {
        val factor = 10.0.pow(5)
        return round(this * factor) / factor
    }

}