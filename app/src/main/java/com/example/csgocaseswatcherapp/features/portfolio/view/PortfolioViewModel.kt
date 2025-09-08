package com.example.csgocaseswatcherapp.features.portfolio.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.features.portfolio.domain.usecases.GetPortfolioDataUseCase
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItem
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItemListArgs
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItemModel
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioValueItem
import com.example.csgocaseswatcherapp.features.sortingmodal.entities.SortingMethod
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

    private var portfolioItemList: List<PortfolioItem> = listOf()

    private var portfolioValueList: List<PortfolioValueItem> = listOf()

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
            is PortfolioViewAction.OnCaseAdded -> handleOnCaseAdded()
            is PortfolioViewAction.OnSortClicked -> handleOnOnSortClicked()
            is PortfolioViewAction.OnSortingMethodSelected -> handleOnSortingMethodSelected(action)
            is PortfolioViewAction.OnPortfolioDetailsClicked -> handleOnPortfolioDetailsClicked()
            is PortfolioViewAction.HideSortingModal -> hideSortingSheet()
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
        val newPortfolioItemList = when (action.sortingMethod) {
            SortingMethod.ByName -> portfolioItemList.sortedBy { it.caseName }
            SortingMethod.ByAmount -> portfolioItemList.sortedByDescending { it.caseAmount }
            SortingMethod.ByPrice -> portfolioItemList.sortedBy { it.casePrice }
            SortingMethod.ByOverallValue -> portfolioItemList.sortedByDescending { it.caseOverallValue }
            SortingMethod.ByProfitLoss -> portfolioItemList.sortedByDescending { it.caseProfitLoss }
        }

        createPortfolioUiState(newPortfolioItemList)

        viewModelScope.launch {
            uiEvent.emit(PortfolioViewEvent.ScrollToTop)
        }
        hideSortingSheet()
    }


    private fun updateContent(transform: (PortfolioViewState.Content) -> PortfolioViewState.Content) {
        val current = uiState.value
        if (current is PortfolioViewState.Content) {
            uiState.value = transform(current)
        }
    }


    private fun handleOnCaseAdded() {
        viewModelScope.launch {
            val state = uiState.value as PortfolioViewState.Content
            uiState.value =
                state.copy(portfolioItemModelList = getPortfolioDataUseCase().map { item -> item.toModel() })
        }
    }

    private fun handleOnOnSortClicked() {
        showSortingSheet()
    }

    private fun showSortingSheet() = updateContent { it.copy(isSortingSheetVisible = true) }
    private fun hideSortingSheet() = updateContent { it.copy(isSortingSheetVisible = false) }

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
            portfolioValueList = portfolioValueList,
            portfolioBartEntryList = mockBarEntry,
            totalPortfolioValue = totalPortfolioValue.formatTotalValue(),
            portfolioItemModelList = portfolioItemList.map { item -> item.toModel() }
        )
    }

    private fun createPortfolioUiState(newPortfolioItemList: List<PortfolioItem>) {
        val prev = uiState.value as? PortfolioViewState.Content
        val portfolioBarEntryList = mapToBarEntry(portfolioValueList)
        val totalPortfolioValue = getTotalValue(newPortfolioItemList)
        uiState.value = PortfolioViewState.Content(
            portfolioValueList = portfolioValueList,
            portfolioBartEntryList = portfolioBarEntryList,
            totalPortfolioValue = totalPortfolioValue.formatTotalValue(),
            portfolioItemModelList = newPortfolioItemList.map { it.toModel() },
            isSortingSheetVisible = prev?.isSortingSheetVisible ?: false
        )
    }

    private fun Double.formatTotalValue(): String {
        return "Total: " + String.format(Locale.US, "$%.2f", this)
    }

    fun PortfolioItem.toModel(): PortfolioItemModel {
        return PortfolioItemModel(
            itemImage = caseImage,
            itemName = caseName,
            totalValue = String.format(Locale.US, "$%.2f", caseOverallValue),
            amountPrice = "$caseAmount cases • ${
                String.format(Locale.US, "$%.2f", casePrice)
            }",
            profitLoss = "${if (caseProfitLoss >= 0) "+" else ""}${
                String.format(Locale.US, "%.2f", caseProfitLoss)
            } $ (${caseProfitLoss} %)"
        )
    }

    private suspend fun getPortfolioData() {
        portfolioItemList = getPortfolioDataUseCase()
    }
}
