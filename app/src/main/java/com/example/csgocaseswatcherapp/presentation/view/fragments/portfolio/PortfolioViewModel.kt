package com.example.csgocaseswatcherapp.presentation.view.fragments.portfolio

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.data.api.ApiTools
import com.example.csgocaseswatcherapp.data.model.portfolioitem.PortfolioItemDtoMapper
import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioItem
import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioItemMapper
import com.example.csgocaseswatcherapp.presentation.view.fragments.caseoverview.CaseOverviewViewState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PortfolioViewModel : ViewModel() {

    private var portfolioItemList: List<PortfolioItem> = listOf()

    val uiState: MutableStateFlow<PortfolioViewState> =
        MutableStateFlow(value = PortfolioViewState.Loading)

    val uiEvent = MutableSharedFlow<PortfolioViewEvent>()

    init {
        viewModelScope.launch {
            try {
                getPortfolioData()
                showContent(portfolioItemList)
            } catch (throwable: Throwable) {
                showError()
                Log.e("Logging_getCaseList", "${throwable.message}")
            }
        }
    }

    private fun showError() {
        uiState.value = PortfolioViewState.Error
    }

    fun handleAction(action: PortfolioViewAction) {
        when (action) {
            is PortfolioViewAction.OnAddCaseClicked -> handleOnAddCaseClicked()
            is PortfolioViewAction.OnCaseAdded -> handleOnCaseAdded(action)
            is PortfolioViewAction.OnCaseNameSortClicked -> handleOnCaseNameSortClicked()
            is PortfolioViewAction.OnCaseAmountClicked -> handleOnCaseAmountClicked()
            is PortfolioViewAction.OnCasePriceClicked -> handleOnCasePriceClicked()
            is PortfolioViewAction.OnCaseOverallValueClicked -> handleOnCaseOverallValueClicked()
            is PortfolioViewAction.OnCaseProfitLossClicked -> handleProfitLossClicked()
        }
    }

    private fun handleProfitLossClicked() {
        val portfolioItem = initialList.sortedByDescending { portfolioItem ->
            portfolioItem.caseProfitLoss
        }
        uiState.value = PortfolioViewState.Content(portfolioItem)
    }

    private fun handleOnCaseOverallValueClicked() {
        val portfolioItem = initialList.sortedByDescending { portfolioItem ->
            portfolioItem.caseOverallValue
        }
        uiState.value = PortfolioViewState.Content(portfolioItem)
    }

    private fun handleOnCasePriceClicked() {
        val portfolioItem = initialList.sortedBy { portfolioItem ->
            portfolioItem.casePrice
        }
        uiState.value = PortfolioViewState.Content(portfolioItem)
    }

    private fun handleOnCaseAmountClicked() {
        val portfolioItem = initialList.sortedByDescending { portfolioItem ->
            portfolioItem.caseAmount
        }
        uiState.value = PortfolioViewState.Content(portfolioItem)
    }

    private fun handleOnCaseNameSortClicked() {
        val portfolioItem = initialList.sortedBy { portfolioItem ->
            portfolioItem.caseName
        }
        uiState.value = PortfolioViewState.Content(portfolioItem)
    }

    private fun handleOnCaseAdded(action: PortfolioViewAction.OnCaseAdded) {
        val portfolioItem = PortfolioItemMapper.map(action.addedCase)
        val newPortfolioItemList = portfolioItemList + portfolioItem
        uiState.value = PortfolioViewState.Content(newPortfolioItemList)
    }

    private fun handleOnAddCaseClicked() {
        viewModelScope.launch { uiEvent.emit(PortfolioViewEvent.NavigateToAddCase) }
    }

    private val initialList = portfolioItemList


    private fun showContent(portfolioItemList: List<PortfolioItem>) {
        uiState.value = PortfolioViewState.Content(portfolioItemList)
    }


    suspend fun getPortfolioData() {
        val responseDto = ApiTools.getApiService().getPortfolioData()
        portfolioItemList = responseDto.map { caseDto ->
            PortfolioItemDtoMapper.map(caseDto)
        }
    }
}