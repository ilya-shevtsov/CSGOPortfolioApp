package com.example.csgocaseswatcherapp.features.caseanalyticsdetails.view

import com.example.csgocaseswatcherapp.features.caseanalytics.view.entities.CaseAnalyticsModel

sealed class CaseAnalyticsDetailsViewState {

    object Loading : CaseAnalyticsDetailsViewState()

    data class Content(
        val caseAnalyticsModel: CaseAnalyticsModel
    ) : CaseAnalyticsDetailsViewState()
}