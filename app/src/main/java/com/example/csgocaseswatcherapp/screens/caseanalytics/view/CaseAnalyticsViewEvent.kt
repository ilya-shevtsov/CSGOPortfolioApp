package com.example.csgocaseswatcherapp.screens.caseanalytics.view

import com.example.csgocaseswatcherapp.screens.caseanalytics.view.entities.CaseAnalyticsModel

sealed class CaseAnalyticsViewEvent {

    data class NavigateToCaseAnalyticsDetails(
        val case: CaseAnalyticsModel
    ) : CaseAnalyticsViewEvent()
}