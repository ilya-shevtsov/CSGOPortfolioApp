package com.example.csgocaseswatcherapp.features.caseoverview.view

import com.example.csgocaseswatcherapp.features.caseoverview.view.model.CaseOverviewModel


sealed class CaseOverviewEvent {

    data class NavigateToCaseDetails(
        val case: CaseOverviewModel
    ) : CaseOverviewEvent()
}