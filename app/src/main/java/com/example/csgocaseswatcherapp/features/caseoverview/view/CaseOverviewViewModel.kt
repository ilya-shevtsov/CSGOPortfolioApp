package com.example.csgocaseswatcherapp.features.caseoverview.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.features.caseoverview.view.entities.CaseOverviewModelMapper
import com.example.csgocaseswatcherapp.features.caseoverview.view.entities.CaseOverviewModel
import com.example.csgocaseswatcherapp.features.caseoverview.domain.usecases.GetCaseOverviewListUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class CaseOverviewViewModel @Inject constructor(
    private val getCaseListUseCase: GetCaseOverviewListUseCase
) : ViewModel() {

    val uiState: MutableStateFlow<CaseOverviewViewState> =
        MutableStateFlow(value = initState())

    val uiEvent = MutableSharedFlow<CaseOverviewViewEvent>()

    init {
        viewModelScope.launch {
            runCatching {
                getCaseListUseCase()
            }.onSuccess { response ->
                uiState.value = CaseOverviewViewState.Content(
                    caseOverviewItemList = response.map(CaseOverviewModelMapper::map)
                )
            }.onFailure { t ->
                if (t is CancellationException) throw t
                Log.e("Logging_getCaseList", t.message ?: "error", t)
                showError()

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

    private fun initState(): CaseOverviewViewState {
        return CaseOverviewViewState.Loading
    }

    private fun showError() {
        uiState.value = CaseOverviewViewState.Error
    }
}
