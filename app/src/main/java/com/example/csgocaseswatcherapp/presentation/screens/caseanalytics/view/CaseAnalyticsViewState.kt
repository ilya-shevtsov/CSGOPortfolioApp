package com.example.csgocaseswatcherapp.presentation.screens.caseanalytics.view

import com.example.csgocaseswatcherapp.presentation.model.caseanalyticsitem.CaseAnalyticsModel

sealed class CaseAnalyticsViewState {

    object Loading : CaseAnalyticsViewState()

    data class Content(
        val caseAnalyticsItemList: List<CaseAnalyticsModel>
    ) : CaseAnalyticsViewState()

    object Error : CaseAnalyticsViewState()
}
