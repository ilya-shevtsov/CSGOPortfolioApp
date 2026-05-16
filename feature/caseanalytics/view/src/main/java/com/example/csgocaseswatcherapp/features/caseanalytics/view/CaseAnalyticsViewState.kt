package com.example.csgocaseswatcherapp.features.caseanalytics.view

import com.example.csgocaseswatcherapp.features.caseanalytics.view.model.CaseAnalyticsModel

sealed class CaseAnalyticsViewState {

    data object Loading : CaseAnalyticsViewState()

    data class Content(
        val caseAnalyticsItemList: List<CaseAnalyticsModel>
    ) : CaseAnalyticsViewState()

    data object Error : CaseAnalyticsViewState()
}
