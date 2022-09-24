package com.example.csgocaseswatcherapp.presentation.screens.caseanalyticsdetails.view

import com.example.csgocaseswatcherapp.presentation.model.caseanalyticsitem.CaseAnalyticsModel

sealed class CaseAnalyticsDetailsViewState {

    object Loading : CaseAnalyticsDetailsViewState()

    data class Content(
        val caseAnalyticsModel: CaseAnalyticsModel
    ) : CaseAnalyticsDetailsViewState()
}