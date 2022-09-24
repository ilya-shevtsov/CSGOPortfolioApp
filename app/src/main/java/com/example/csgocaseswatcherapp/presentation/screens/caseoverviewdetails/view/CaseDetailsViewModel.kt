package com.example.csgocaseswatcherapp.presentation.screens.caseoverviewdetails.view

import androidx.lifecycle.ViewModel
import com.example.csgocaseswatcherapp.presentation.screens.caseoverview.view.entities.CaseOverviewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class CaseDetailsViewModel @Inject constructor(
) : ViewModel() {

    val uiState: MutableStateFlow<CaseDetailsViewState> =
        MutableStateFlow(value = CaseDetailsViewState.Loading)

    fun handleAction(action: CaseDetailsViewAction) {
        when (action) {
            is CaseDetailsViewAction.OnItemProvided -> handleOnItemProvided(action.caseOverviewModel)

        }
    }

    private fun handleOnItemProvided(caseOverviewModel: CaseOverviewModel) {
        val state = CaseDetailsViewState.Content(
            caseOverviewModel
        )
        uiState.value = state
    }
}
