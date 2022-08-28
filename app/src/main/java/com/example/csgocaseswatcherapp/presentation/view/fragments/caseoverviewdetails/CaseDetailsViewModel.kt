package com.example.csgocaseswatcherapp.presentation.view.fragments.caseoverviewdetails

import androidx.lifecycle.ViewModel
import com.example.csgocaseswatcherapp.presentation.model.caseoverviewitem.CaseOverviewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class CaseDetailsViewModel @Inject constructor(
) : ViewModel() {

    val uiState: MutableStateFlow<CaseDetailsViewState> =
        MutableStateFlow(value = CaseDetailsViewState.Loading)

    fun onItemProvided(currentCase: CaseOverviewModel) {
        val state = CaseDetailsViewState.Content(
            currentCase
        )
        uiState.value = state
    }
}
