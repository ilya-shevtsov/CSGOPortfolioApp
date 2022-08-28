package com.example.csgocaseswatcherapp.data.repository

import com.example.csgocaseswatcherapp.data.api.ApiTools
import com.example.csgocaseswatcherapp.data.model.caseanalytics.CaseAnalyticsMapper
import com.example.csgocaseswatcherapp.domain.model.caseanalytics.CaseAnalytics
import com.example.csgocaseswatcherapp.domain.repository.CaseAnalyticsRepository
import javax.inject.Inject

class CaseAnalyticsRepositoryImpl @Inject constructor(

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

