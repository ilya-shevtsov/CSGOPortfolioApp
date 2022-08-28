package com.example.csgocaseswatcherapp.presentation.view.fragments.caseoverviewdetails

import com.example.csgocaseswatcherapp.presentation.model.caseoverviewitem.CaseOverviewModel

sealed class CaseDetailsViewState {

    object Loading : CaseDetailsViewState()

    data class Content(
        val caseOverviewModel: CaseOverviewModel
    ) : CaseDetailsViewState()

}

