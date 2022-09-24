package com.example.csgocaseswatcherapp.presentation.view.fragments.caseoverview.view

import com.example.csgocaseswatcherapp.presentation.model.caseoverviewitem.CaseOverviewModel

sealed class CaseOverviewViewEvent {

    data class NavigateToCaseDetails(
        val case: CaseOverviewModel
    ) : CaseOverviewViewEvent()
}