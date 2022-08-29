package com.example.csgocaseswatcherapp.presentation.view.fragments.caseoverview

import com.example.csgocaseswatcherapp.presentation.model.caseoverviewitem.CaseOverviewModel

sealed class CaseOverviewViewState {

    object Loading : CaseOverviewViewState()

    data class Content(
        val caseOverviewItemList: List<CaseOverviewModel>,
    ) : CaseOverviewViewState()

    object Error : CaseOverviewViewState()
}
