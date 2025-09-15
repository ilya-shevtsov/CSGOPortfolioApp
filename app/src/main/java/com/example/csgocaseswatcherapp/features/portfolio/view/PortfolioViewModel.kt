package com.example.csgocaseswatcherapp.features.portfolio.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.features.portfolio.domain.usecases.GetPortfolioDataUseCase
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItem
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItemListArgs
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItemModel
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioValueItem
import com.example.csgocaseswatcherapp.features.sortingmodal.entities.SortState
import com.github.mikephil.charting.data.BarEntry
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject
import kotlin.math.roundToLong

class PortfolioViewModel @Inject constructor(
    private val getPortfolioDataUseCase: GetPortfolioDataUseCase
) : ViewModel() {

    val uiState: MutableStateFlow<PortfolioViewState> =
        MutableStateFlow(value = PortfolioViewState.Loading)

    val uiEvent = MutableSharedFlow<PortfolioViewEvent>()

    private val businessState = MutableStateFlow(
        initBusinessState()
    )

    init {
        createViewStateChain()
    }

    private fun initBusinessState(): PortfolioState {
        return PortfolioState(
            portfolioItemListResult = PortfolioItemListResult.Loading,
            portfolioValueList = listOf(),
            portfolioBartEntryList = listOf(),
            totalPortfolioValue = "",
            isSortingSheetVisible = false,
            sortState = SortState.OVERALL_VALUE
        )
    }

    private fun createViewStateChain() {
        businessState.onEach { state ->
            val uiState = when (state.portfolioItemListResult) {
                is PortfolioItemListResult.Error -> PortfolioViewState.Error
                is PortfolioItemListResult.Loading -> PortfolioViewState.Loading
                is PortfolioItemListResult.Success -> {

                    val models = state.portfolioItemListResult.portfolioItemList
                        .sortBySortState(state.sortState)
                        .map { it.toModel() }

                    val totalPortfolioValue =
                        state.portfolioItemListResult.portfolioItemList.sumOf { case ->
                            case.overallValue.roundToLong().toDouble()
                        }.formatTotalValue()

                    PortfolioViewState.Content(
                        portfolioItemModelList = models,
                        portfolioBartEntryList = mockBarEntry,
                        totalPortfolioValue = totalPortfolioValue,
                        isSortingSheetVisible = state.isSortingSheetVisible
                    )
                }
            }
            this.uiState.value = uiState
        }.launchIn(viewModelScope)
    }

    fun handleAction(action: PortfolioViewAction) {
        when (action) {
            is PortfolioViewAction.OnCreate -> onCreate()
            is PortfolioViewAction.OnAddCaseClicked -> handleOnAddCaseClicked()
            is PortfolioViewAction.OnCaseAdded -> handleOnCaseAdded()
            is PortfolioViewAction.OnSortClicked -> handleOnOnSortClicked()
            is PortfolioViewAction.OnSortingMethodSelected -> handleOnSortingMethodSelected(action)
            is PortfolioViewAction.OnPortfolioDetailsClicked -> handleOnPortfolioDetailsClicked()
            is PortfolioViewAction.HideSortingModal -> hideSortingSheet()

        }
    }

    private fun onCreate() {
        viewModelScope.launch {
            initPortfolioDataLoad()
        }
    }

    private suspend fun initPortfolioDataLoad() {
        try {
            val portfolioData = getPortfolioDataUseCase.invoke()
            businessState.update { state ->
                state.copy(
                    portfolioItemListResult = PortfolioItemListResult.Success(portfolioData),
                    portfolioValueList = listOf(),
                    portfolioBartEntryList = listOf(),
                    totalPortfolioValue = "",
                    isSortingSheetVisible = false,
                    sortState = SortState.OVERALL_VALUE
                )
            }
        } catch (throwable: Throwable) {
            businessState.update { state ->
                state.copy(
                    portfolioItemListResult = PortfolioItemListResult.Error(
                        errorMessage = throwable
                            .message
                    )
                )
            }
        }
    }

    private fun List<PortfolioItem>.sortBySortState(state: SortState): List<PortfolioItem> =
        when (state) {
            SortState.NAME -> sortedBy { it.name }
            SortState.AMOUNT -> sortedByDescending { it.amount }
            SortState.PRICE -> sortedByDescending { it.price }
            SortState.OVERALL_VALUE -> sortedByDescending { it.overallValue }
            SortState.PROFIT_LOSS -> sortedByDescending { it.profitLoss }
        }

    // currently unused, but will be in the future (maybe lol)
    private fun mapToBarEntry(portfolioValueList: List<PortfolioValueItem>): List<BarEntry> {
        return portfolioValueList.map { value ->
            BarEntry(value.date, value.value)
        }
    }


    private fun handleOnPortfolioDetailsClicked() {
        val currentBusinessState =
            businessState.value.portfolioItemListResult as PortfolioItemListResult.Success
        val portfolioItemListArgs = PortfolioItemListArgs(currentBusinessState.portfolioItemList)
        viewModelScope.launch {
            uiEvent.emit(
                PortfolioViewEvent.NavigateToPortfolioDetails(
                    portfolioItemListArgs
                )
            )
        }
    }

    private fun hideSortingSheet() {
        businessState.update { state -> state.copy(isSortingSheetVisible = false) }
    }

    private fun handleOnSortingMethodSelected(action: PortfolioViewAction.OnSortingMethodSelected) {
        businessState.update { state ->
            state.copy(sortState = action.sortState, isSortingSheetVisible = false)
        }
        viewModelScope.launch {
            uiEvent.emit(PortfolioViewEvent.ScrollToTop)
        }
    }

    private fun handleOnCaseAdded() {
        viewModelScope.launch {
            runCatching { getPortfolioDataUseCase.invoke() }
                .onSuccess { portfolioData ->
                    businessState.update { state ->
                        state.copy(
                            portfolioItemListResult = PortfolioItemListResult.Success(portfolioData)
                        )
                    }
                }
                .onFailure { error ->
                    businessState.update { state ->
                        state.copy(
                            portfolioItemListResult = PortfolioItemListResult.Error(error.message)
                        )
                    }
                }
        }
    }

    private fun handleOnOnSortClicked() {
        businessState.update { state -> state.copy(isSortingSheetVisible = true) }
    }

    private fun handleOnAddCaseClicked() {
        viewModelScope.launch { uiEvent.emit(PortfolioViewEvent.NavigateToAddCase) }
    }

    private fun Double.formatTotalValue(): String {
        return "Total: " + String.format(Locale.US, "$%.2f", this)
    }

    private fun PortfolioItem.toModel(): PortfolioItemModel {
        return PortfolioItemModel(
            itemImage = image,
            itemName = name,
            totalValue = String.format(Locale.US, "$%.2f", overallValue),
            amountPrice = "$amount cases • ${
                String.format(Locale.US, "$%.2f", price)
            }",
            profitLoss = "${if (profitLoss >= 0) "+" else ""}${
                String.format(Locale.US, "%.2f", profitLoss)
            } $ (${profitLoss} %)"
        )
    }

    private val mockBarEntry = listOf(
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
}
