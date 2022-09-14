package com.example.csgocaseswatcherapp.presentation.view.fragments.caseoverviewdetails

import com.example.csgocaseswatcherapp.presentation.model.caseoverviewitem.CaseOverviewModel

sealed class CaseDetailsViewAction {

    data class OnItemProvided(
        val caseOverviewModel: CaseOverviewModel
    ) : CaseDetailsViewAction()
}