package com.example.csgocaseswatcherapp.presentation.view.fragments.portfolio

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.data.api.ApiTools
import com.example.csgocaseswatcherapp.data.model.portfolioitem.PortfolioItemDtoMapper
import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioItem
import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioItemMapper
import com.github.mikephil.charting.data.PieEntry
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PortfolioViewModel : ViewModel() {

    var portfolioItemList: List<PortfolioItem> = listOf()

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

    private fun mapToPieEntry(PortfolioItemList: List<PortfolioItem>): List<PieEntry> {
        return PortfolioItemList.map { case ->
            PieEntry(
                case.caseAmount.toFloat(),
                case.caseName
                    .replace("Operation", "")
                    .replace("Case", "")
            )
        }
    }

    private fun getTotalValue(PortfolioItemList: List<PortfolioItem>): Double {
        return PortfolioItemList.sumOf { case ->
            case.caseOverallValue
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
        val newPortfolioItemList = portfolioItemList.sortedByDescending { portfolioItem ->
            portfolioItem.caseProfitLoss
        }
        val portfolioPietEntryList = mapToPieEntry(portfolioItemList)
        val totalPortfolioValue = getTotalValue(portfolioItemList)
        uiState.value = PortfolioViewState.Content(
            newPortfolioItemList,
            portfolioPietEntryList,
            totalPortfolioValue
        )
    }

    private fun handleOnCaseOverallValueClicked() {
        val newPortfolioItemList = portfolioItemList.sortedByDescending { portfolioItem ->
            portfolioItem.caseOverallValue
        }
        val portfolioPietEntryList = mapToPieEntry(portfolioItemList)
        val totalPortfolioValue = getTotalValue(portfolioItemList)
        uiState.value = PortfolioViewState.Content(
            newPortfolioItemList,
            portfolioPietEntryList,
            totalPortfolioValue
        )
    }

    private fun handleOnCasePriceClicked() {
        val newPortfolioItemList = portfolioItemList.sortedBy { portfolioItem ->
            portfolioItem.casePrice
        }
        val portfolioPietEntryList = mapToPieEntry(portfolioItemList)
        val totalPortfolioValue = getTotalValue(portfolioItemList)
        uiState.value = PortfolioViewState.Content(
            newPortfolioItemList,
            portfolioPietEntryList,
            totalPortfolioValue
        )
    }

    private fun handleOnCaseAmountClicked() {
        val newPortfolioItemList = portfolioItemList.sortedByDescending { portfolioItem ->
            portfolioItem.caseAmount
        }
        val portfolioPietEntryList = mapToPieEntry(portfolioItemList)
        val totalPortfolioValue = getTotalValue(portfolioItemList)
        uiState.value = PortfolioViewState.Content(
            newPortfolioItemList,
            portfolioPietEntryList,
            totalPortfolioValue
        )
    }

    private fun handleOnCaseNameSortClicked() {
        val newPortfolioItemList = portfolioItemList.sortedBy { portfolioItem ->
            portfolioItem.caseName
        }
        val portfolioPietEntryList = mapToPieEntry(portfolioItemList)
        val totalPortfolioValue = getTotalValue(portfolioItemList)
        uiState.value = PortfolioViewState.Content(
            newPortfolioItemList,
            portfolioPietEntryList,
            totalPortfolioValue
        )
    }

    private fun handleOnCaseAdded(action: PortfolioViewAction.OnCaseAdded) {
        val portfolioItem = PortfolioItemMapper.map(action.addedCase)
        val newPortfolioItemList = portfolioItemList + portfolioItem
        val portfolioPietEntryList = mapToPieEntry(portfolioItemList)
        val totalPortfolioValue = getTotalValue(portfolioItemList)
        uiState.value =
            PortfolioViewState.Content(
                newPortfolioItemList,
                portfolioPietEntryList,
                totalPortfolioValue
            )
    }

    private fun handleOnAddCaseClicked() {
        viewModelScope.launch { uiEvent.emit(PortfolioViewEvent.NavigateToAddCase) }
    }

    private fun showContent(
        portfolioItemList: List<PortfolioItem>,
    ) {
        val portfolioPietEntryList = mapToPieEntry(portfolioItemList)
        val totalPortfolioValue = getTotalValue(portfolioItemList)
        uiState.value = PortfolioViewState.Content(
            portfolioItemList = portfolioItemList,
            portfolioPietEntryList = portfolioPietEntryList,
            totalPortfolioValue = totalPortfolioValue

        )
    }


    private suspend fun getPortfolioData() {
        val responseDto = ApiTools.getApiService().getPortfolioData()
        portfolioItemList = responseDto.map { caseDto ->
            PortfolioItemDtoMapper.map(caseDto)
        }
    }
}