package com.example.csgocaseswatcherapp.screens.caseoverview.view

import com.example.csgocaseswatcherapp.screens.caseoverview.view.entities.CaseOverviewModel

sealed class CaseOverviewViewAction {

    data class OnCaseClicked(
        val case: CaseOverviewModel
    ) : CaseOverviewViewAction()
}