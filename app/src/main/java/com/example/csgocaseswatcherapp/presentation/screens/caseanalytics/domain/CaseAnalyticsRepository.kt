package com.example.csgocaseswatcherapp.presentation.screens.caseanalytics.domain

import com.example.csgocaseswatcherapp.presentation.screens.caseanalytics.domain.entities.CaseAnalytics

interface CaseAnalyticsRepository {
    suspend fun getCaseAnalyticsList(): List<CaseAnalytics>
}