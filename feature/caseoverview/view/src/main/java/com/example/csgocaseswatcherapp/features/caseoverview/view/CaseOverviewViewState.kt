package com.example.csgocaseswatcherapp.features.caseoverview.view

import com.example.csgocaseswatcherapp.features.caseoverview.view.model.CaseOverviewModel


sealed class CaseOverviewViewState {

    data object Loading : CaseOverviewViewState()

    data class Content(
        val caseOverviewItemList: List<CaseOverviewModel>,
    ) : CaseOverviewViewState()

    data object Error : CaseOverviewViewState()
}
