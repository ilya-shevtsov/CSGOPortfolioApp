package com.example.csgocaseswatcherapp.features.caseanalytics.data

import com.example.csgocaseswatcherapp.data.api.ApiTools
import com.example.csgocaseswatcherapp.features.caseanalytics.data.entities.CaseAnalyticsMapper
import com.example.csgocaseswatcherapp.features.caseanalytics.domain.entities.CaseAnalytics
import com.example.csgocaseswatcherapp.features.caseanalytics.domain.CaseAnalyticsRepository
import javax.inject.Inject

class CaseAnalyticsServerRepository @Inject constructor(

) : CaseAnalyticsRepository {

    override suspend fun getCaseAnalyticsList(): List<CaseAnalytics> {
        val caseAnalyticsDtoListResponse = ApiTools.getApiService()
            .getCaseAnalyticsList()
        return caseAnalyticsDtoListResponse.map { caseAnalyticsDto ->
            CaseAnalyticsMapper.map(
                caseAnalyticsDto
            )
        }
    }
}

