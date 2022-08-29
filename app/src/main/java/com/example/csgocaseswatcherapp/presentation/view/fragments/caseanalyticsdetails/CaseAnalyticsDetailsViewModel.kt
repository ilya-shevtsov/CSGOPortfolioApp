package com.example.csgocaseswatcherapp.presentation.view.fragments.caseanalyticsdetails

import androidx.lifecycle.ViewModel
import com.example.csgocaseswatcherapp.presentation.model.caseanalyticsitem.CaseAnalyticsModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class CaseAnalyticsDetailsViewModel @Inject constructor(
) : ViewModel() {

    val uiState: MutableStateFlow<CaseAnalyticsDetailsViewState> =
        MutableStateFlow(value = CaseAnalyticsDetailsViewState.Loading)

    fun onItemProvided(currentCase: CaseAnalyticsModel) {
        val state = CaseAnalyticsDetailsViewState.Content(
            currentCase
        )
        uiState.value = state
    }
}
