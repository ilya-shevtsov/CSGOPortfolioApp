package com.example.csgocaseswatcherapp.presentation.view.fragments.caseanalytics.domain

import com.example.csgocaseswatcherapp.presentation.view.fragments.caseanalytics.domain.entities.CaseAnalytics

interface CaseAnalyticsRepository {
    suspend fun getCaseAnalyticsList(): List<CaseAnalytics>
}