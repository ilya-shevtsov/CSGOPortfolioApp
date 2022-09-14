package com.example.csgocaseswatcherapp.presentation.view.fragments.caseanalyticsdetails

import androidx.lifecycle.ViewModel
import com.example.csgocaseswatcherapp.presentation.model.caseanalyticsitem.CaseAnalyticsModel
import com.example.csgocaseswatcherapp.presentation.view.fragments.portfoliodetails.PortfolioDetailsViewAction
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
