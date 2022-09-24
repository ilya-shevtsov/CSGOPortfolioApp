package com.example.csgocaseswatcherapp.presentation.screens.caseanalyticsdetails.view

import com.example.csgocaseswatcherapp.presentation.screens.caseanalytics.view.entities.CaseAnalyticsModel

sealed class CaseAnalyticsDetailsViewState {

    object Loading : CaseAnalyticsDetailsViewState()

    data class Content(
        val caseAnalyticsModel: CaseAnalyticsModel
    ) : CaseAnalyticsDetailsViewState()
}