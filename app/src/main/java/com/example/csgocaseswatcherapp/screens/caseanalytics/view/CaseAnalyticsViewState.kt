package com.example.csgocaseswatcherapp.screens.caseanalytics.view

import com.example.csgocaseswatcherapp.screens.caseanalytics.view.entities.CaseAnalyticsModel

sealed class CaseAnalyticsViewState {

    object Loading : CaseAnalyticsViewState()

    data class Content(
        val caseAnalyticsItemList: List<CaseAnalyticsModel>
    ) : CaseAnalyticsViewState()

    object Error : CaseAnalyticsViewState()
}
