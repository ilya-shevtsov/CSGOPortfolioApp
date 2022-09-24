package com.example.csgocaseswatcherapp.screens.caseanalytics.view

import com.example.csgocaseswatcherapp.screens.caseanalytics.view.entities.CaseAnalyticsModel

sealed class CaseAnalyticsViewAction {

    data class OnCaseClicked(
        val case: CaseAnalyticsModel
    ) : CaseAnalyticsViewAction()
}