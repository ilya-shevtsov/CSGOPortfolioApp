package com.example.csgocaseswatcherapp.features.caseoverview.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.features.caseoverview.domain.usecases.GetCaseOverviewListUseCase
import com.example.csgocaseswatcherapp.features.caseoverview.view.entities.CaseOverviewModel
import com.example.csgocaseswatcherapp.features.caseoverview.view.entities.CaseOverviewModelMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class CaseOverviewViewModel @Inject constructor(
    private val getCaseListUseCase: GetCaseOverviewListUseCase
) : ViewModel() {

    val uiState: MutableStateFlow<CaseOverviewViewState> =
        MutableStateFlow(value = initState())

    val uiEvent = MutableSharedFlow<CaseOverviewEvent>()

    init {
        loadCases()
    }

    private fun loadCases() {
        viewModelScope.launch {
            runCatching {
                val response = withContext(Dispatchers.IO) {
                    getCaseListUseCase()
                }

                withContext(Dispatchers.Default) {
                    response.map(CaseOverviewModelMapper::map)
                }
            }.onSuccess { caseOverviewItemList ->
                uiState.update {
                    CaseOverviewViewState.Content(
                        caseOverviewItemList = caseOverviewItemList
                    )
                }
            }.onFailure { throwable ->
                if (throwable is CancellationException) throw throwable
                showError()
            }
        }
    }

    fun handleAction(action: CaseOverviewAction) {
        when (action) {
            is CaseOverviewAction.OnCaseClicked -> handleOnCaseClicked(action.case)
        }
    }

    private fun handleOnCaseClicked(case: CaseOverviewModel) {
        viewModelScope.launch {
            uiEvent.emit(CaseOverviewEvent.NavigateToCaseDetails(case))
        }
    }

    private fun initState(): CaseOverviewViewState {
        return CaseOverviewViewState.Loading
    }

    private fun showError() {
        uiState.value = CaseOverviewViewState.Error
    }
}
