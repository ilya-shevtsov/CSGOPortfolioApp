package com.example.csgocaseswatcherapp.presentation.screens.caseoverview.view

import com.example.csgocaseswatcherapp.presentation.screens.caseoverview.view.entities.CaseOverviewModel

sealed class CaseOverviewViewEvent {

    data class NavigateToCaseDetails(
        val case: CaseOverviewModel
    ) : CaseOverviewViewEvent()
}