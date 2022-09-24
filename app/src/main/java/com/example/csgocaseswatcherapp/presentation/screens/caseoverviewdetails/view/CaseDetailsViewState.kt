package com.example.csgocaseswatcherapp.presentation.screens.caseoverviewdetails.view

import com.example.csgocaseswatcherapp.presentation.screens.caseoverview.view.entities.CaseOverviewModel

sealed class CaseDetailsViewState {

    object Loading : CaseDetailsViewState()

    data class Content(
        val caseOverviewModel: CaseOverviewModel
    ) : CaseDetailsViewState()
}

