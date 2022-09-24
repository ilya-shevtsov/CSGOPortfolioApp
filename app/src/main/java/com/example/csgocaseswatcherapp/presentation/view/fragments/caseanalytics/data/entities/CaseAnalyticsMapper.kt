package com.example.csgocaseswatcherapp.presentation.view.fragments.caseanalytics.data.entities

import com.example.csgocaseswatcherapp.presentation.view.fragments.caseanalytics.domain.entities.CaseAnalytics

object CaseAnalyticsMapper {

    fun map(
        caseAnalyticsDto: CaseAnalyticsDto,
    ): CaseAnalytics {
        return CaseAnalytics(
            caseName = caseAnalyticsDto.name,
            dailyAvgReturnInPercent = caseAnalyticsDto.dailyAvgReturnInPercent,
            dailyAvgReturnInRUB = caseAnalyticsDto.dailyAvgReturnInRUB,
            dailyStandardDeviation = caseAnalyticsDto.dailyStandardDeviation,
            dailySharpRatio = caseAnalyticsDto.dailySharpRatio,
            monthlyAvgReturnInPercent = caseAnalyticsDto.monthlyAvgReturnInPercent,
            monthlyAvgReturnInRUB = caseAnalyticsDto.monthlyAvgReturnInRUB,
            monthlyStandardDeviation = caseAnalyticsDto.monthlyStandardDeviation,
            monthlySharpRatio = caseAnalyticsDto.monthlySharpRatio,
            )
    }
}