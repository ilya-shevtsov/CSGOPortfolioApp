package com.example.csgocaseswatcherapp.screens.caseanalytics.view.entities

import com.example.csgocaseswatcherapp.screens.caseanalytics.domain.entities.CaseAnalytics

object CaseAnalyticsItemMapper {

    fun map(caseAnalytics: CaseAnalytics): CaseAnalyticsModel {
        return CaseAnalyticsModel(
            caseName = caseAnalytics.caseName,
            dailyAvgReturnInPercent = caseAnalytics.dailyAvgReturnInPercent,
            dailyAvgReturnInRUB = caseAnalytics.dailyAvgReturnInRUB,
            dailyStandardDeviation = caseAnalytics.dailyStandardDeviation,
            dailySharpRatio = caseAnalytics.dailySharpRatio,
            monthlyAvgReturnInPercent = caseAnalytics.monthlyAvgReturnInPercent,
            monthlyAvgReturnInRUB = caseAnalytics.monthlyAvgReturnInRUB,
            monthlyStandardDeviation = caseAnalytics.monthlyStandardDeviation,
            monthlySharpRatio = caseAnalytics.monthlySharpRatio,
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
}