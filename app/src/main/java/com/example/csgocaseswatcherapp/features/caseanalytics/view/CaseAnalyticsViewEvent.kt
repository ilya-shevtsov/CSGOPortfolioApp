package com.example.csgocaseswatcherapp.features.caseanalytics.view

import com.example.csgocaseswatcherapp.features.caseanalytics.view.entities.CaseAnalyticsModel

sealed class CaseAnalyticsViewEvent {

    data class NavigateToCaseAnalyticsDetails(
        val case: CaseAnalyticsModel
    ) : CaseAnalyticsViewEvent()
}