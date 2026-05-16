package com.example.csgocaseswatcherapp.features.caseanalytics.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.features.caseanalytics.domain.usecases.GetCaseAnalyticsListUseCase
import com.example.csgocaseswatcherapp.features.caseanalytics.view.model.CaseAnalyticsItemMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class CaseAnalyticsViewModel @Inject constructor(
    private val getCaseAnalyticsListUseCase: GetCaseAnalyticsListUseCase
) : ViewModel() {

    val uiState: MutableStateFlow<CaseAnalyticsViewState> =
        MutableStateFlow(value = initState())

    init {
        loadCaseAnalytics()
    }

    private fun loadCaseAnalytics() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    getCaseAnalyticsListUseCase.getCaseAnalyticsList()
                }

                val caseAnalyticsItemList = withContext(Dispatchers.Default) {
                    response.map(CaseAnalyticsItemMapper::map)
                }

                uiState.update {
                    CaseAnalyticsViewState.Content(
                        caseAnalyticsItemList = caseAnalyticsItemList
                    )
                }
            } catch (throwable: Throwable) {
                if (throwable is CancellationException) throw throwable
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
