package com.example.csgocaseswatcherapp.screens.caseanalyticsdetails.view

import com.example.csgocaseswatcherapp.screens.caseanalytics.view.entities.CaseAnalyticsModel

sealed class CaseAnalyticsDetailsViewState {

    object Loading : CaseAnalyticsDetailsViewState()

    data class Content(
        val caseAnalyticsModel: CaseAnalyticsModel
    ) : CaseAnalyticsDetailsViewState()
}