package com.example.csgocaseswatcherapp.presentation.screens.caseanalytics.view

import com.example.csgocaseswatcherapp.presentation.model.caseanalyticsitem.CaseAnalyticsModel

sealed class CaseAnalyticsViewEvent {

    data class NavigateToCaseAnalyticsDetails(
        val case: CaseAnalyticsModel
    ) : CaseAnalyticsViewEvent()
}