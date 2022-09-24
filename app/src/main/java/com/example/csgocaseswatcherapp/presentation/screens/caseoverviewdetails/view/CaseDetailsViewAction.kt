package com.example.csgocaseswatcherapp.presentation.screens.caseoverviewdetails.view

import com.example.csgocaseswatcherapp.presentation.model.caseoverviewitem.CaseOverviewModel

sealed class CaseDetailsViewAction {

    data class OnItemProvided(
        val caseOverviewModel: CaseOverviewModel
    ) : CaseDetailsViewAction()
}