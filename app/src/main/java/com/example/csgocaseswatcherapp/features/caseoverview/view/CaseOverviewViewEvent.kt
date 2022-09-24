package com.example.csgocaseswatcherapp.features.caseoverview.view

import com.example.csgocaseswatcherapp.features.caseoverview.view.entities.CaseOverviewModel

sealed class CaseOverviewViewEvent {

    data class NavigateToCaseDetails(
        val case: CaseOverviewModel
    ) : CaseOverviewViewEvent()
}