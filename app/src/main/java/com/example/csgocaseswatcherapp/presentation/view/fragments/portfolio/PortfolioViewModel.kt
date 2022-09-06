package com.example.csgocaseswatcherapp.presentation.view.fragments.portfolio

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.data.api.ApiTools
import com.example.csgocaseswatcherapp.data.model.portfolioitem.PortfolioItemDtoMapper
import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioGroupieItem
import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioItemMapper
import com.example.csgocaseswatcherapp.presentation.model.portfoliodetailsvalueitem.PortfolioValueItem
import com.example.csgocaseswatcherapp.presentation.view.fragments.sortingbottomsheetfragment.SortingMethod
import com.github.mikephil.charting.data.BarEntry
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PortfolioViewModel : ViewModel() {

    var portfolioItemList: List<PortfolioGroupieItem> = listOf()

    var portfolioValueList: List<PortfolioValueItem> = listOf()

    val uiState: MutableStateFlow<PortfolioViewState> =
        MutableStateFlow(value = PortfolioViewState.Loading)

    val uiEvent = MutableSharedFlow<PortfolioViewEvent>()

    init {
        viewModelScope.launch {
            try {
                getPortfolioData()
                showContent(portfolioItemList, portfolioValueList)
            } catch (throwable: Throwable) {
                showError()
                Log.e("Logging_getCaseList", "${throwable.message}")
            }
        }
    }

    private fun mapToBarEntry(portfolioValueList: List<PortfolioValueItem>): List<BarEntry> {
        return portfolioValueList.map { value ->
            BarEntry(value.date, value.value)
        }
    }

    private fun getTotalValue(PortfolioItemList: List<PortfolioGroupieItem>): Double {
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
            is PortfolioViewAction.OnSortClicked -> handleOnOnSortClicked()
            is PortfolioViewAction.OnSortingMethodSelected -> handleOnSortingMethodSelected(action)
//            is PortfolioViewAction.OnCaseAmountClicked -> handleOnCaseAmountClicked()
//            is PortfolioViewAction.OnCasePriceClicked -> handleOnCasePriceClicked()
//            is PortfolioViewAction.OnCaseOverallValueClicked -> handleOnCaseOverallValueClicked()
//            is PortfolioViewAction.OnCaseProfitLossClicked -> handleProfitLossClicked()
        }
    }

    private fun handleOnSortingMethodSelected(action: PortfolioViewAction.OnSortingMethodSelected) {
        when (action.sortingMethod) {
            SortingMethod.byName -> {
                val newPortfolioItemList = portfolioItemList.sortedBy { portfolioItem ->
                    portfolioItem.caseName
                }
                val portfolioBarEntryList = mapToBarEntry(portfolioValueList)
                val totalPortfolioValue = getTotalValue(portfolioItemList)
                uiState.value = PortfolioViewState.Content(
                    portfolioItemList = newPortfolioItemList,
                    portfolioValueList = portfolioValueList,
                    portfolioBartEntryList = portfolioBarEntryList,
                    totalPortfolioValue = totalPortfolioValue
                )
            }
            SortingMethod.byAmount -> {
                val newPortfolioItemList = portfolioItemList.sortedByDescending { portfolioItem ->
                    portfolioItem.caseAmount
                }
                val portfolioBarEntryList = mapToBarEntry(portfolioValueList)
                val totalPortfolioValue = getTotalValue(portfolioItemList)
                uiState.value = PortfolioViewState.Content(
                    portfolioItemList = newPortfolioItemList,
                    portfolioValueList = portfolioValueList,
                    portfolioBartEntryList = portfolioBarEntryList,
                    totalPortfolioValue = totalPortfolioValue
                )
            }
            SortingMethod.byPrice -> {
                val newPortfolioItemList = portfolioItemList.sortedBy { portfolioItem ->
                    portfolioItem.casePrice
                }
                val portfolioBarEntryList = mapToBarEntry(portfolioValueList)
                val totalPortfolioValue = getTotalValue(portfolioItemList)
                uiState.value = PortfolioViewState.Content(
                    portfolioItemList = newPortfolioItemList,
                    portfolioValueList = portfolioValueList,
                    portfolioBartEntryList = portfolioBarEntryList,
                    totalPortfolioValue = totalPortfolioValue
                )
            }
            SortingMethod.byOverallValue -> {
                val newPortfolioItemList = portfolioItemList.sortedByDescending { portfolioItem ->
                    portfolioItem.caseOverallValue
                }
                val portfolioBarEntryList = mapToBarEntry(portfolioValueList)
                val totalPortfolioValue = getTotalValue(portfolioItemList)
                uiState.value = PortfolioViewState.Content(
                    portfolioItemList = newPortfolioItemList,
                    portfolioValueList = portfolioValueList,
                    portfolioBartEntryList = portfolioBarEntryList,
                    totalPortfolioValue = totalPortfolioValue
                )
            }
            SortingMethod.byPorfitLoss -> {
                val newPortfolioItemList = portfolioItemList.sortedByDescending { portfolioItem ->
                    portfolioItem.caseProfitLoss
                }
                val portfolioBarEntryList = mapToBarEntry(portfolioValueList)
                val totalPortfolioValue = getTotalValue(portfolioItemList)
                uiState.value = PortfolioViewState.Content(
                    portfolioItemList = newPortfolioItemList,
                    portfolioValueList = portfolioValueList,
                    portfolioBartEntryList = portfolioBarEntryList,
                    totalPortfolioValue = totalPortfolioValue
                )
            }
        }
    }

    private fun handleOnCaseAdded(action: PortfolioViewAction.OnCaseAdded) {
        val portfolioItem = PortfolioItemMapper.map(action.addedCase)
        val newPortfolioItemList = portfolioItemList + portfolioItem
        val portfolioBarEntryList = mapToBarEntry(portfolioValueList)
        val totalPortfolioValue = getTotalValue(portfolioItemList)
        uiState.value = PortfolioViewState.Content(
            portfolioItemList = newPortfolioItemList,
            portfolioValueList = portfolioValueList,
            portfolioBartEntryList = portfolioBarEntryList,
            totalPortfolioValue = totalPortfolioValue
        )
    }

    private fun handleOnOnSortClicked() {
        viewModelScope.launch { uiEvent.emit(PortfolioViewEvent.NavigateToSorting) }
    }

    private fun handleOnAddCaseClicked() {
        viewModelScope.launch { uiEvent.emit(PortfolioViewEvent.NavigateToAddCase) }
    }


    private fun showContent(
        portfolioItemList: List<PortfolioGroupieItem>, portfolioValueList: List<PortfolioValueItem>
    ) {
        val portfolioBarEntryList = mapToBarEntry(portfolioValueList)
        val totalPortfolioValue = getTotalValue(portfolioItemList)
        uiState.value = PortfolioViewState.Content(
            portfolioItemList = portfolioItemList,
            portfolioValueList = portfolioValueList,
            portfolioBartEntryList = portfolioBarEntryList,
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