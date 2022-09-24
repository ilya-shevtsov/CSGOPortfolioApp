package com.example.csgocaseswatcherapp.presentation.screens.caseoverview.view

import com.example.csgocaseswatcherapp.presentation.screens.caseoverview.view.entities.CaseOverviewModel

sealed class CaseOverviewViewAction {

    data class OnCaseClicked(
        val case: CaseOverviewModel
    ) : CaseOverviewViewAction()
}