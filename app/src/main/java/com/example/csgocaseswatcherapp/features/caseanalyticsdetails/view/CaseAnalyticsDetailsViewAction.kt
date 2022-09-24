package com.example.csgocaseswatcherapp.features.caseanalyticsdetails.view

import com.example.csgocaseswatcherapp.features.caseanalytics.view.entities.CaseAnalyticsModel

sealed class CaseAnalyticsDetailsViewAction {

    data class OnItemProvided(
        val caseAnalyticsModel: CaseAnalyticsModel
    ) : CaseAnalyticsDetailsViewAction()
}