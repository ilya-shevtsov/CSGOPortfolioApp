package com.example.csgocaseswatcherapp.screens.caseoverview.view

import com.example.csgocaseswatcherapp.screens.caseoverview.view.entities.CaseOverviewModel

sealed class CaseOverviewViewEvent {

    data class NavigateToCaseDetails(
        val case: CaseOverviewModel
    ) : CaseOverviewViewEvent()
}