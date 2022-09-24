package com.example.csgocaseswatcherapp.features.caseanalytics.view

import com.example.csgocaseswatcherapp.features.caseanalytics.view.entities.CaseAnalyticsModel

sealed class CaseAnalyticsViewState {

    object Loading : CaseAnalyticsViewState()

    data class Content(
        val caseAnalyticsItemList: List<CaseAnalyticsModel>
    ) : CaseAnalyticsViewState()

    object Error : CaseAnalyticsViewState()
}
