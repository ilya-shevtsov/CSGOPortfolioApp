package com.example.csgocaseswatcherapp.features.caseoverview.view

import com.example.csgocaseswatcherapp.features.caseoverview.view.entities.CaseOverviewModel

sealed class CaseOverviewViewState {

    object Loading : CaseOverviewViewState()

    data class Content(
        val caseOverviewItemList: List<CaseOverviewModel>,
    ) : CaseOverviewViewState()

    object Error : CaseOverviewViewState()
}
