package com.example.csgocaseswatcherapp.presentation.screens.caseanalytics.view

import com.example.csgocaseswatcherapp.presentation.model.caseanalyticsitem.CaseAnalyticsModel

sealed class CaseAnalyticsViewAction {

    data class OnCaseClicked(
        val case: CaseAnalyticsModel
    ) : CaseAnalyticsViewAction()
}