package com.example.csgocaseswatcherapp.features.caseoverview.view

import com.example.csgocaseswatcherapp.features.caseoverview.view.entities.CaseOverviewModel

sealed class CaseOverviewViewAction {

    data class OnCaseClicked(
        val case: CaseOverviewModel
    ) : CaseOverviewViewAction()
}