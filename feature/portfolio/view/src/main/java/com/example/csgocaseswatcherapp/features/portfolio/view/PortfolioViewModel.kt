package com.example.csgocaseswatcherapp.features.portfolio.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.features.portfolio.domain.entities.PortfolioItem
import com.example.csgocaseswatcherapp.features.portfolio.domain.model.PortfolioItemsResult
import com.example.csgocaseswatcherapp.features.portfolio.domain.model.PortfolioSortType
import com.example.csgocaseswatcherapp.features.portfolio.domain.model.PortfolioState
import com.example.csgocaseswatcherapp.features.portfolio.domain.model.profitLossPercent
import com.example.csgocaseswatcherapp.features.portfolio.domain.model.sortBy
import com.example.csgocaseswatcherapp.features.portfolio.domain.usecases.GetPortfolioDataUseCase
import com.example.csgocaseswatcherapp.features.portfolio.view.model.PortfolioItemModel
import com.example.csgocaseswatcherapp.features.portfolio.view.model.PortfolioValueItem
import com.github.mikephil.charting.data.BarEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val getPortfolioDataUseCase: GetPortfolioDataUseCase
) : ViewModel() {

    val uiState: MutableStateFlow<PortfolioViewState> =
        MutableStateFlow(value = PortfolioViewState.Loading)

    val uiEvent = MutableSharedFlow<PortfolioEvent>()


    private val businessState = MutableStateFlow(
        initBusinessState()
    )
    private val isSortingSheetVisible = MutableStateFlow(false)

    init {
        createViewStateChain()
    }

    private fun initBusinessState(): PortfolioState {
        return PortfolioState(
            portfolioItemsResult = PortfolioItemsResult.Loading,
            totalPortfolioValue = 0.0,
            sortType = PortfolioSortType.OVERALL_VALUE
        )
    }

    private fun createViewStateChain() {
        combine(
            businessState,
            isSortingSheetVisible
        ) { state, isSortingSheetVisible ->

            when (val result = state.portfolioItemsResult) {
                is PortfolioItemsResult.Error -> PortfolioViewState.Error
                is PortfolioItemsResult.Loading -> PortfolioViewState.Loading
                is PortfolioItemsResult.Success -> {
                    PortfolioViewState.Content(
                        portfolioItemModelList = result.portfolioItemList
                            .sortBy(state.sortType)
                            .map { it.toModel() },
                        portfolioBartEntryList = mockBarEntry,
                        totalPortfolioValue = state.totalPortfolioValue,
                        isSortingSheetVisible = isSortingSheetVisible
                    )
                }
            }
        }.onEach { viewState ->
            uiState.value = viewState
        }.launchIn(viewModelScope)
    }

    fun handleAction(action: PortfolioAction) {
        when (action) {
            is PortfolioAction.OnCreate -> onCreate()
            is PortfolioAction.OnAddCaseClicked -> handleOnAddCaseClicked()
            is PortfolioAction.OnCaseAdded -> handleOnCaseAdded()
            is PortfolioAction.OnSortClicked -> handleOnOnSortClicked()
            is PortfolioAction.OnSortingMethodSelected -> handleOnSortingMethodSelected(action)
            is PortfolioAction.OnPortfolioDetailsClicked -> handleOnPortfolioDetailsClicked()
            is PortfolioAction.HideSortingModal -> hideSortingSheet()

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
                    portfolioItemsResult = PortfolioItemsResult.Success(portfolioData),
                    totalPortfolioValue = 0.0,
                    sortType = PortfolioSortType.OVERALL_VALUE
                )
            }
        } catch (throwable: Throwable) {
            businessState.update { state ->
                state.copy(portfolioItemsResult = PortfolioItemsResult.Error(message = throwable.message))
            }
        }
    }

    // currently unused, but will be in the future (maybe lol)
    private fun mapToBarEntry(portfolioValueList: List<PortfolioValueItem>): List<BarEntry> {
        return portfolioValueList.map { value ->
            BarEntry(value.date, value.value)
        }
    }


    private fun handleOnPortfolioDetailsClicked() {
        val currentBusinessState = businessState.value.portfolioItemsResult as PortfolioItemsResult.Success

        viewModelScope.launch {
            uiEvent.emit(PortfolioEvent.NavigateToPortfolioDetails(currentBusinessState.portfolioItemList))
        }
    }

    private fun hideSortingSheet() {
        isSortingSheetVisible.update { false }
    }

    private fun handleOnSortingMethodSelected(action: PortfolioAction.OnSortingMethodSelected) {
        businessState.update { state ->
            state.copy(sortType = action.sortType)
        }

        isSortingSheetVisible.update { false }

        viewModelScope.launch {
            uiEvent.emit(PortfolioEvent.ScrollToTop)
        }
    }

    private fun handleOnCaseAdded() {
        viewModelScope.launch {
            runCatching { getPortfolioDataUseCase.invoke() }
                .onSuccess { portfolioData ->
                    businessState.update { state ->
                        state.copy(
                            portfolioItemsResult = PortfolioItemsResult.Success(portfolioData)
                        )
                    }
                }
                .onFailure { error ->
                    businessState.update { state ->
                        state.copy(
                            portfolioItemsResult = PortfolioItemsResult.Error(error.message)
                        )
                    }
                }
        }
    }

    private fun handleOnOnSortClicked() {
        isSortingSheetVisible.update { true }
    }

    private fun handleOnAddCaseClicked() {
        viewModelScope.launch { uiEvent.emit(PortfolioEvent.NavigateToAddCase) }
    }

    private fun PortfolioItem.toModel(): PortfolioItemModel {
        return PortfolioItemModel(
            itemImage = image,
            itemName = name,
            totalValue = overallValue,
            amount = amount,
            price = price,
            profitLoss = profitLoss,
            profitLossPercent = profitLossPercent
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
