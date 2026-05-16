package com.example.csgocaseswatcherapp.features.caseoverviewdetails.view

import com.example.csgocaseswatcherapp.features.caseoverview.view.model.CaseOverviewModel

sealed class CaseDetailsViewAction {

    data class OnItemProvided(
        val caseOverviewModel: CaseOverviewModel
    ) : CaseDetailsViewAction()
}