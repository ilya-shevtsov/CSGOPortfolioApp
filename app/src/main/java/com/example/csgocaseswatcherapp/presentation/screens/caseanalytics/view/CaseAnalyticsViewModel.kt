package com.example.csgocaseswatcherapp.presentation.screens.caseanalytics.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.presentation.screens.caseanalytics.domain.entities.CaseAnalytics
import com.example.csgocaseswatcherapp.presentation.screens.caseanalytics.domain.usecases.GetCaseAnalyticsListUseCase
import com.example.csgocaseswatcherapp.presentation.model.caseanalyticsitem.CaseAnalyticsItemMapper
import com.example.csgocaseswatcherapp.presentation.model.caseanalyticsitem.CaseAnalyticsModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CaseAnalyticsViewModel @Inject constructor(
    private val getCaseAnalyticsListUseCase: GetCaseAnalyticsListUseCase
) : ViewModel() {

    val uiState: MutableStateFlow<CaseAnalyticsViewState> =
        MutableStateFlow(value = CaseAnalyticsViewState.Loading)

    val uiEvent = MutableSharedFlow<CaseAnalyticsViewEvent>()

    init {
        viewModelScope.launch {
            try {
                val response = getCaseAnalyticsListUseCase.getCaseAnalyticsList()
                showCaseAnalyticsList(response)
            } catch (throwable: Throwable) {
                showError()
                Log.e("Logging_CasesAnalyticsViewModel.getCaseList", "${throwable.message}")

            }
        }
    }

    fun handleAction(action: CaseAnalyticsViewAction) {
        when (action) {
            is CaseAnalyticsViewAction.OnCaseClicked -> handleOnCaseClicked(action.case)
        }
    }

    private fun handleOnCaseClicked(case: CaseAnalyticsModel) {
        viewModelScope.launch {
            uiEvent.emit(CaseAnalyticsViewEvent.NavigateToCaseAnalyticsDetails(case))
        }
    }

    private fun showError() {
        uiState.value = CaseAnalyticsViewState.Error
    }

    private fun showCaseAnalyticsList(caseAnalyticsList: List<CaseAnalytics>) {
        uiState.value = CaseAnalyticsViewState.Content(
            caseAnalyticsItemList = caseAnalyticsList.map(CaseAnalyticsItemMapper::map),
        )
    }
}
