package com.example.csgocaseswatcherapp.screens.caseoverview.view

import com.example.csgocaseswatcherapp.screens.caseoverview.view.entities.CaseOverviewModel

sealed class CaseOverviewViewState {

    object Loading : CaseOverviewViewState()

    data class Content(
        val caseOverviewItemList: List<CaseOverviewModel>,
    ) : CaseOverviewViewState()

    object Error : CaseOverviewViewState()
}
