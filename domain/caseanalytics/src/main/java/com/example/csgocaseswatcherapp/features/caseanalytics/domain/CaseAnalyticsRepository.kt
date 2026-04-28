package com.example.csgocaseswatcherapp.features.caseanalytics.domain

import com.example.csgocaseswatcherapp.features.caseanalytics.domain.entities.CaseAnalytics

interface CaseAnalyticsRepository {
    suspend fun getCaseAnalyticsList(): List<CaseAnalytics>
}