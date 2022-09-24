package com.example.csgocaseswatcherapp.presentation.screens.caseoverview.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.presentation.screens.caseoverview.view.entities.CaseOverviewItemMapper
import com.example.csgocaseswatcherapp.presentation.screens.caseoverview.view.entities.CaseOverviewModel
import com.example.csgocaseswatcherapp.presentation.screens.caseoverview.domain.entities.CaseOverview
import com.example.csgocaseswatcherapp.presentation.screens.caseoverview.domain.usecases.GetCaseOverviewListUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CaseOverviewViewModel @Inject constructor(
    private val getCaseListUseCase: GetCaseOverviewListUseCase
) : ViewModel() {

    val uiState: MutableStateFlow<CaseOverviewViewState> =
        MutableStateFlow(value = CaseOverviewViewState.Loading)

    val uiEvent = MutableSharedFlow<CaseOverviewViewEvent>()

    init {
        viewModelScope.launch {
            try {
                val response = getCaseListUseCase.getCaseOverviewList()
                showCaseList(response)
            } catch (throwable: Throwable) {
                showError()
                Log.e("Logging_getCaseList", "${throwable.message}")
            }
        }
    }

    fun handleAction(action: CaseOverviewViewAction) {
        when (action) {
            is CaseOverviewViewAction.OnCaseClicked -> handleOnCaseClicked(action.case)
        }
    }

    private fun handleOnCaseClicked(case: CaseOverviewModel) {
        viewModelScope.launch {
            uiEvent.emit(CaseOverviewViewEvent.NavigateToCaseDetails(case))
        }
    }

    private fun showError() {
        uiState.value = CaseOverviewViewState.Error
    }

    private fun showCaseList(caseList: List<CaseOverview>) {
        uiState.value = CaseOverviewViewState.Content(
            caseOverviewItemList = caseList.map(CaseOverviewItemMapper::map),
        )
    }
}