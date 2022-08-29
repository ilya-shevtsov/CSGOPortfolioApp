package com.example.csgocaseswatcherapp.presentation.view.fragments.caseanalyticsdetails

import com.example.csgocaseswatcherapp.presentation.model.caseanalyticsitem.CaseAnalyticsModel

sealed class CaseAnalyticsDetailsViewState {

    object Loading : CaseAnalyticsDetailsViewState()

    data class Content(
        val caseAnalyticsModel: CaseAnalyticsModel
    ) : CaseAnalyticsDetailsViewState()

}