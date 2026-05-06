package com.example.csgocaseswatcherapp.features.portfoliodetails.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.features.portfolio.domain.entities.PortfolioItem
import com.example.csgocaseswatcherapp.features.portfoliodetails.domain.PortfolioDetailsState
import com.github.mikephil.charting.data.PieEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PortfolioDetailsViewModel @Inject constructor() : ViewModel() {

    val uiState: MutableStateFlow<PortfolioDetailsViewState> =
        MutableStateFlow(value = PortfolioDetailsViewState.Loading)

    private val businessState = MutableStateFlow(
        initBusinessState()
    )

    init {
        createViewStateChain()
    }

    private fun initBusinessState(): PortfolioDetailsState {
        return PortfolioDetailsState(portfolioItemList = listOf())
    }

    fun handleAction(action: PortfolioDetailsAction) {
        when (action) {
            is PortfolioDetailsAction.OnPortfolioDataProvided -> handleOnPortfolioDataProvided(
                action.portfolioItemList
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

    private fun handleOnPortfolioDataProvided(portfolioItemList: List<PortfolioItem>) {
        businessState.update { state ->
            state.copy(portfolioItemList = portfolioItemList)
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

