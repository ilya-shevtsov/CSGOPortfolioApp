package com.example.csgocaseswatcherapp.presentation.view.fragments.caseoverview

import com.example.csgocaseswatcherapp.presentation.model.caseoverviewitem.CaseOverviewModel

sealed class CaseOverviewViewAction {

    data class OnCaseClicked(
        val case: CaseOverviewModel
    ) : CaseOverviewViewAction()
}