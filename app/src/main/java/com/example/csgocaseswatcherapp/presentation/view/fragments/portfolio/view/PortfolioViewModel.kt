package com.example.csgocaseswatcherapp.presentation.view.fragments.portfolio.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.presentation.model.PortfolioItemListArgs
import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioCaseItem
import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioItemMapper
import com.example.csgocaseswatcherapp.presentation.model.portfoliodetailsvalueitem.PortfolioValueItem
import com.example.csgocaseswatcherapp.presentation.view.fragments.portfolio.domain.GetPortfolioDataUseCase
import com.example.csgocaseswatcherapp.presentation.view.fragments.sortingbottomsheetfragment.SortingMethod
import com.github.mikephil.charting.data.BarEntry
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToLong

class PortfolioViewModel @Inject constructor(
    private val getPortfolioDataUseCase: GetPortfolioDataUseCase
) :
    ViewModel() {

    var portfolioItemList: List<PortfolioCaseItem> = listOf()

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

    private fun getTotalValue(PortfolioItemList: List<PortfolioCaseItem>): Double {
        return PortfolioItemList.sumOf { case ->
            case.caseOverallValue.roundToLong().toDouble()
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
            is PortfolioViewAction.OnPortfolioDetailsClicked -> handleOnPortfolioDetailsClicked()
        }
    }

    private fun handleOnPortfolioDetailsClicked() {
        val portfolioItemListArgs = PortfolioItemListArgs(portfolioItemList)
        viewModelScope.launch {
            uiEvent.emit(
                PortfolioViewEvent.NavigateToPortfolioDetails(
                    portfolioItemListArgs
                )
            )
        }
    }

    private fun handleOnSortingMethodSelected(action: PortfolioViewAction.OnSortingMethodSelected) {
        when (action.sortingMethod) {
            SortingMethod.ByName -> {
                val newPortfolioItemList = portfolioItemList.sortedBy { portfolioItem ->
                    portfolioItem.caseName
                }
                createPortfolioUiState(newPortfolioItemList)
            }
            SortingMethod.ByAmount -> {
                val newPortfolioItemList = portfolioItemList.sortedByDescending { portfolioItem ->
                    portfolioItem.caseAmount
                }
                createPortfolioUiState(newPortfolioItemList)
            }
            SortingMethod.ByPrice -> {
                val newPortfolioItemList = portfolioItemList.sortedBy { portfolioItem ->
                    portfolioItem.casePrice
                }
                createPortfolioUiState(newPortfolioItemList)
            }
            SortingMethod.ByOverallValue -> {
                val newPortfolioItemList = portfolioItemList.sortedByDescending { portfolioItem ->
                    portfolioItem.caseOverallValue
                }
                createPortfolioUiState(newPortfolioItemList)
            }
            SortingMethod.ByProfitLoss -> {
                val newPortfolioItemList = portfolioItemList.sortedByDescending { portfolioItem ->
                    portfolioItem.caseProfitLoss
                }
                createPortfolioUiState(newPortfolioItemList)
            }
        }
    }

    private fun handleOnCaseAdded(action: PortfolioViewAction.OnCaseAdded) {
        val portfolioItem = PortfolioItemMapper.map(action.addedCase)
        val newPortfolioItemList = portfolioItemList + portfolioItem
        createPortfolioUiState(newPortfolioItemList)
    }

    private fun handleOnOnSortClicked() {
        viewModelScope.launch { uiEvent.emit(PortfolioViewEvent.NavigateToSorting) }
    }

    private fun handleOnAddCaseClicked() {
        viewModelScope.launch { uiEvent.emit(PortfolioViewEvent.NavigateToAddCase) }
    }

    private fun showContent(
        portfolioItemList: List<PortfolioCaseItem>, portfolioValueList: List<PortfolioValueItem>
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

    private fun createPortfolioUiState(newPortfolioItemList: List<PortfolioCaseItem>) {
        val portfolioBarEntryList = mapToBarEntry(portfolioValueList)
        val totalPortfolioValue = getTotalValue(portfolioItemList)
        uiState.value = PortfolioViewState.Content(
            portfolioItemList = newPortfolioItemList,
            portfolioValueList = portfolioValueList,
            portfolioBartEntryList = portfolioBarEntryList,
            totalPortfolioValue = totalPortfolioValue
        )
    }

    private suspend fun getPortfolioData() {
        portfolioItemList = getPortfolioDataUseCase.invoke()
    }
}