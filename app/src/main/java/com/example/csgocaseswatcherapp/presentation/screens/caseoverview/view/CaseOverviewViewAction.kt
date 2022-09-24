package com.example.csgocaseswatcherapp.presentation.screens.caseoverview.view

import com.example.csgocaseswatcherapp.presentation.model.caseoverviewitem.CaseOverviewModel

sealed class CaseOverviewViewAction {

    data class OnCaseClicked(
        val case: CaseOverviewModel
    ) : CaseOverviewViewAction()
}