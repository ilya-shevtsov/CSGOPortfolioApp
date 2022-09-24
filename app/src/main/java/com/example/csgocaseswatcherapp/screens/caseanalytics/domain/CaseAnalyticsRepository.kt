package com.example.csgocaseswatcherapp.screens.caseanalytics.domain

import com.example.csgocaseswatcherapp.screens.caseanalytics.domain.entities.CaseAnalytics

interface CaseAnalyticsRepository {
    suspend fun getCaseAnalyticsList(): List<CaseAnalytics>
}