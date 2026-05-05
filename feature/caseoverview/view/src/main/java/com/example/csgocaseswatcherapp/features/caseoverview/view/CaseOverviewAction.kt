package com.example.csgocaseswatcherapp.features.caseoverview.view

import com.example.csgocaseswatcherapp.features.caseoverview.view.model.CaseOverviewModel


sealed class CaseOverviewAction {

    data class OnCaseClicked(
        val case: CaseOverviewModel
    ) : CaseOverviewAction()
}