package com.example.csgocaseswatcherapp.screens.caseanalyticsdetails.view

import com.example.csgocaseswatcherapp.screens.caseanalytics.view.entities.CaseAnalyticsModel

sealed class CaseAnalyticsDetailsViewAction {

    data class OnItemProvided(
        val caseAnalyticsModel: CaseAnalyticsModel
    ) : CaseAnalyticsDetailsViewAction()
}