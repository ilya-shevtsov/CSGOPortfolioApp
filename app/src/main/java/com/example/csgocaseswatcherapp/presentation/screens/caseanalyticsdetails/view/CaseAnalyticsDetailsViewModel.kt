package com.example.csgocaseswatcherapp.presentation.screens.caseanalyticsdetails.view

import androidx.lifecycle.ViewModel
import com.example.csgocaseswatcherapp.presentation.screens.caseanalytics.view.entities.CaseAnalyticsModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class CaseAnalyticsDetailsViewModel @Inject constructor(
) : ViewModel() {

    val uiState: MutableStateFlow<CaseAnalyticsDetailsViewState> =
        MutableStateFlow(value = CaseAnalyticsDetailsViewState.Loading)

    fun handleAction(action: CaseAnalyticsDetailsViewAction) {
        when (action) {
            is CaseAnalyticsDetailsViewAction.OnItemProvided -> handleOnItemProvided(action.caseAnalyticsModel)

        }
    }

    private fun handleOnItemProvided(caseAnalyticsModel: CaseAnalyticsModel) {
        val state = CaseAnalyticsDetailsViewState.Content(
            caseAnalyticsModel
        )
        uiState.value = state
    }
}
