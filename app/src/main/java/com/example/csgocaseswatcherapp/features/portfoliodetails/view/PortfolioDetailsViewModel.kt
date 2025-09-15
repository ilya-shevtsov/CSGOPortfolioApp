package com.example.csgocaseswatcherapp.features.portfoliodetails.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItemListArgs
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItem
import com.example.csgocaseswatcherapp.features.portfoliodetails.domain.PortfolioDetailsState
import com.github.mikephil.charting.data.PieEntry
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class PortfolioDetailsViewModel @Inject constructor() : ViewModel() {

    val uiState: MutableStateFlow<PortfolioDetailsViewState> =
        MutableStateFlow(value = PortfolioDetailsViewState.Loading)

    val uiEvent = MutableSharedFlow<PortfolioDetailsViewEvent>()

    private val businessState = MutableStateFlow(
        initBusinessState()
    )

    init {
        createViewStateChain()
    }

    private fun initBusinessState(): PortfolioDetailsState {
        return PortfolioDetailsState(portfolioItemList = listOf())
    }

    fun handleAction(action: PortfolioDetailsViewAction) {
        when (action) {
            is PortfolioDetailsViewAction.OnPortfolioDataProvided -> handleOnPortfolioDataProvided(
                action.portfolioItemListArgs
            )
        }
    }

    private fun createViewStateChain() {
        businessState.onEach { state ->
            val uiState = PortfolioDetailsViewState.Content(
                portfolioPietEntryList = state.portfolioItemList.toPieEntry()
            )
            this.uiState.value = uiState
        }.launchIn(viewModelScope)
    }

    private fun handleOnPortfolioDataProvided(portfolioItemListArgs: PortfolioItemListArgs) {
        businessState.update { state ->
            state.copy(portfolioItemList = portfolioItemListArgs.portfolioItemList)
        }
    }

    private fun List<PortfolioItem>.toPieEntry(): List<PieEntry> {
        return this.map { case ->
            PieEntry(
                case.amount.toFloat(),
                case.name
                    .replace("Operation", "")
                    .replace("Case", "")
            )
        }
    }
}

