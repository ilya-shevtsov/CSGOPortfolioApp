package com.example.csgocaseswatcherapp.features.caseanalytics.data

import com.example.csgocaseswatcherapp.api.ServerApi
import com.example.csgocaseswatcherapp.features.caseanalytics.data.entities.CaseAnalyticsMapper
import com.example.csgocaseswatcherapp.features.caseanalytics.domain.CaseAnalyticsRepository
import com.example.csgocaseswatcherapp.features.caseanalytics.domain.entities.CaseAnalytics
import javax.inject.Inject

class CaseAnalyticsServerRepository @Inject constructor(
    private val api: ServerApi
) : CaseAnalyticsRepository {

    override suspend fun getCaseAnalyticsList(): List<CaseAnalytics> {
        val caseAnalyticsDtoListResponse = api
            .getCaseAnalyticsList()
        return caseAnalyticsDtoListResponse.map { caseAnalyticsDto ->
            CaseAnalyticsMapper.map(
                caseAnalyticsDto
            )
        }
    }
}

