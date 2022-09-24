package com.example.csgocaseswatcherapp.features.caseanalytics.view

import com.example.csgocaseswatcherapp.features.caseanalytics.view.entities.CaseAnalyticsModel

sealed class CaseAnalyticsViewAction {

    data class OnCaseClicked(
        val case: CaseAnalyticsModel
    ) : CaseAnalyticsViewAction()
}