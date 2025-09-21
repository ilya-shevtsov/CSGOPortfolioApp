package com.example.csgocaseswatcherapp.features.caseanalytics.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.features.caseanalytics.domain.usecases.GetCaseAnalyticsListUseCase
import com.example.csgocaseswatcherapp.features.caseanalytics.view.entities.CaseAnalyticsItemMapper
import com.example.csgocaseswatcherapp.features.caseanalytics.view.entities.CaseAnalyticsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CaseAnalyticsViewModel @Inject constructor(
    private val getCaseAnalyticsListUseCase: GetCaseAnalyticsListUseCase
) : ViewModel() {

    val uiState: MutableStateFlow<CaseAnalyticsViewState> =
        MutableStateFlow(value = initState())

    val uiEvent = MutableSharedFlow<CaseAnalyticsViewEvent>()

    init {
        viewModelScope.launch {
            try {
                val response = getCaseAnalyticsListUseCase.getCaseAnalyticsList()
                uiState.value = CaseAnalyticsViewState.Content(
                    caseAnalyticsItemList = response.map(CaseAnalyticsItemMapper::map),
                )
            } catch (throwable: Throwable) {
                showError()
            }
        }
    }

    private fun initState(): CaseAnalyticsViewState {
        return CaseAnalyticsViewState.Loading
    }

    private fun showError() {
        uiState.value = CaseAnalyticsViewState.Error
    }
}
