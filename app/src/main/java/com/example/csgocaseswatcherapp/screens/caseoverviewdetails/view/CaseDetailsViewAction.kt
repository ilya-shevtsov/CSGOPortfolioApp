package com.example.csgocaseswatcherapp.screens.caseoverviewdetails.view

import com.example.csgocaseswatcherapp.screens.caseoverview.view.entities.CaseOverviewModel

sealed class CaseDetailsViewAction {

    data class OnItemProvided(
        val caseOverviewModel: CaseOverviewModel
    ) : CaseDetailsViewAction()
}