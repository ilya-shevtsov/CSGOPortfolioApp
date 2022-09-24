package com.example.csgocaseswatcherapp.presentation.view.fragments.caseanalytics.view

import com.example.csgocaseswatcherapp.presentation.model.caseanalyticsitem.CaseAnalyticsModel

sealed class CaseAnalyticsViewState {

    object Loading : CaseAnalyticsViewState()

    data class Content(
        val caseAnalyticsItemList: List<CaseAnalyticsModel>
    ) : CaseAnalyticsViewState()

    object Error : CaseAnalyticsViewState()
}
