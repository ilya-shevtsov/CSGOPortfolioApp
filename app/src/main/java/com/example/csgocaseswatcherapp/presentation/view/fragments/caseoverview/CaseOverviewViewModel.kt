package com.example.csgocaseswatcherapp.presentation.view.fragments.caseoverview

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.domain.model.caseoverview.CaseOverview
import com.example.csgocaseswatcherapp.domain.usecase.GetCaseOverviewListUseCase
import com.example.csgocaseswatcherapp.presentation.model.caseoverviewitem.CaseOverviewItemMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CaseOverviewViewModel @Inject constructor(
    private val getCaseListUseCase: GetCaseOverviewListUseCase
) : ViewModel() {

    val uiState: MutableStateFlow<CaseOverviewViewState> =
        MutableStateFlow(value = CaseOverviewViewState.Loading)

    init {
        viewModelScope.launch {
            try {
                val response = getCaseListUseCase.getCaseOverviewList()
                showCaseList(response)
            } catch (throwable: Throwable){
                showError()
                Log.e("Logging_CasesOverviewViewModel.getCaseList", "${throwable.message}")
            }
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