package com.example.csgocaseswatcherapp.features.portfolio.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.features.portfolio.domain.usecases.GetPortfolioDataUseCase
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItem
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItemListArgs
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioValueItem
import com.example.csgocaseswatcherapp.features.sortingmodal.view.SortingMethod
import com.github.mikephil.charting.data.BarEntry
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject
import kotlin.math.roundToLong

class PortfolioViewModel @Inject constructor(
    private val getPortfolioDataUseCase: GetPortfolioDataUseCase
) :
    ViewModel() {

    var portfolioItemList: List<PortfolioItem> = listOf()

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

    private fun getTotalValue(portfolioItemList: List<PortfolioItem>): Double {
        return portfolioItemList.sumOf { case ->
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
        viewModelScope.launch {
            val state = uiState.value as PortfolioViewState.Content
            uiState.value = state.copy(portfolioItemList = getPortfolioDataUseCase())
        }
    }

    private fun handleOnOnSortClicked() {
        viewModelScope.launch { uiEvent.emit(PortfolioViewEvent.NavigateToSorting) }
    }

    private fun handleOnAddCaseClicked() {
        viewModelScope.launch { uiEvent.emit(PortfolioViewEvent.NavigateToAddCase) }
    }

    private fun showContent(
        portfolioItemList: List<PortfolioItem>, portfolioValueList: List<PortfolioValueItem>
    ) {
        val portfolioBarEntryList = mapToBarEntry(portfolioValueList)
        val mockBarEntry = listOf(
            BarEntry(1f, 129f),
            BarEntry(2f, 164f),
            BarEntry(3f, 225f),
            BarEntry(4f, 236f),
            BarEntry(5f, 334f),
            BarEntry(6f, 479f),
            BarEntry(7f, 429f),
            BarEntry(8f, 424f),
            BarEntry(9f, 448f),
            BarEntry(10f, 335f),
            BarEntry(11f, 315f),
            BarEntry(12f, 322f),
            BarEntry(13f, 414f),
            BarEntry(14f, 458f),
            BarEntry(15f, 509f),
            BarEntry(16f, 546f),
            BarEntry(17f, 668f),
            BarEntry(18f, 741f),
            BarEntry(19f, 685f),
            BarEntry(20f, 840f),
            BarEntry(21f, 834f),
        )
        val totalPortfolioValue = getTotalValue(portfolioItemList)
        uiState.value = PortfolioViewState.Content(
            portfolioItemList = portfolioItemList,
            portfolioValueList = portfolioValueList,
            portfolioBartEntryList = mockBarEntry,
            totalPortfolioValue = totalPortfolioValue.formatTotalValue()
        )
    }

    private fun createPortfolioUiState(newPortfolioItemList: List<PortfolioItem>) {
        val portfolioBarEntryList = mapToBarEntry(portfolioValueList)
        val totalPortfolioValue = getTotalValue(portfolioItemList)
        uiState.value = PortfolioViewState.Content(
            portfolioItemList = newPortfolioItemList,
            portfolioValueList = portfolioValueList,
            portfolioBartEntryList = portfolioBarEntryList,
            totalPortfolioValue = totalPortfolioValue.formatTotalValue()
        )
    }


    private fun Double.formatTotalValue():String {
        return "Total: " + String.format(Locale.US, "$%.2f", this)
    }

    private suspend fun getPortfolioData() {
        portfolioItemList = getPortfolioDataUseCase()
    }
}
