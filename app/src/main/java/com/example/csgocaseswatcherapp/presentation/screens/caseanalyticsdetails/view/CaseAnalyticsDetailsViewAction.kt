package com.example.csgocaseswatcherapp.presentation.screens.caseanalyticsdetails.view

import com.example.csgocaseswatcherapp.presentation.screens.caseanalytics.view.entities.CaseAnalyticsModel

sealed class CaseAnalyticsDetailsViewAction {

    data class OnItemProvided(
        val caseAnalyticsModel: CaseAnalyticsModel
    ) : CaseAnalyticsDetailsViewAction()
}