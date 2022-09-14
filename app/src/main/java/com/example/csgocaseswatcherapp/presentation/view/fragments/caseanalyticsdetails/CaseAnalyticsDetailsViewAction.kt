package com.example.csgocaseswatcherapp.presentation.view.fragments.caseanalyticsdetails

import com.example.csgocaseswatcherapp.presentation.model.caseanalyticsitem.CaseAnalyticsModel

sealed class CaseAnalyticsDetailsViewAction {

    data class OnItemProvided(
        val caseAnalyticsModel: CaseAnalyticsModel
    ) : CaseAnalyticsDetailsViewAction()
}