package com.example.csgocaseswatcherapp.screens.caseanalytics.domain.usecases

import com.example.csgocaseswatcherapp.screens.caseanalytics.domain.entities.CaseAnalytics
import com.example.csgocaseswatcherapp.screens.caseanalytics.domain.CaseAnalyticsRepository
import javax.inject.Inject

class GetCaseAnalyticsListUseCase @Inject constructor(
    private val caseAnalyticsRepository: CaseAnalyticsRepository
) {

    suspend fun getCaseAnalyticsList(): List<CaseAnalytics> {
        return caseAnalyticsRepository.getCaseAnalyticsList()
    }
}

