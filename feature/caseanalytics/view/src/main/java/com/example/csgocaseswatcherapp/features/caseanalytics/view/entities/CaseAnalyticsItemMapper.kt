package com.example.csgocaseswatcherapp.features.caseanalytics.view.entities

import com.example.csgocaseswatcherapp.features.caseanalytics.domain.entities.CaseAnalytics
import kotlin.math.pow
import kotlin.math.round

object CaseAnalyticsItemMapper {

    fun map(caseAnalytics: CaseAnalytics): CaseAnalyticsModel {
        return CaseAnalyticsModel(
            caseName = caseAnalytics.caseName,
            dailyAvgReturnInPercent = "${caseAnalytics.dailyAvgReturnInPercent} %",
            dailyAvgReturnInRUB = caseAnalytics.dailyAvgReturnInRUB.toString(),
            dailyStandardDeviation = caseAnalytics.dailyStandardDeviation.toDoubleWith5Decimals().toString(),
            dailySharpRatio = caseAnalytics.dailySharpRatio.toDoubleWith5Decimals().toString(),
            monthlyAvgReturnInPercent = "${caseAnalytics.monthlyAvgReturnInPercent} %",
            monthlyAvgReturnInRUB = caseAnalytics.monthlyAvgReturnInRUB.toString(),
            monthlyStandardDeviation = caseAnalytics.monthlyStandardDeviation.toDoubleWith5Decimals().toString(),
            monthlySharpRatio = caseAnalytics.monthlySharpRatio.toDoubleWith5Decimals().toString(),
            imageUrl = getCaseImage(caseAnalytics.caseName)
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