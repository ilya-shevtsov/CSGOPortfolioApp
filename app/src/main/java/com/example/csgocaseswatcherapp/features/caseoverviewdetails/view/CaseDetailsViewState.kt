package com.example.csgocaseswatcherapp.features.caseoverviewdetails.view

import com.example.csgocaseswatcherapp.features.caseoverview.view.entities.CaseOverviewModel

sealed class CaseDetailsViewState {

    object Loading : CaseDetailsViewState()

    data class Content(
        val caseOverviewModel: CaseOverviewModel
    ) : CaseDetailsViewState()
}

