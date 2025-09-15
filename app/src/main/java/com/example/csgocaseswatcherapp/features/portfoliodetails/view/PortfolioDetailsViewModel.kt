package com.example.csgocaseswatcherapp.features.portfoliodetails.view

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItemListArgs
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItem
import com.github.mikephil.charting.data.PieEntry
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class PortfolioDetailsViewModel @Inject constructor() : ViewModel() {

    var portfolioItemList: List<PortfolioItem> = listOf()

    val uiState: MutableStateFlow<PortfolioDetailsViewState> =
        MutableStateFlow(value = PortfolioDetailsViewState.Loading)

    val uiEvent = MutableSharedFlow<PortfolioDetailsViewEvent>()

    private fun mapToPieEntry(portfolioItemList: List<PortfolioItem>): List<PieEntry> {
        return portfolioItemList.map { case ->
            PieEntry(
                case.amount.toFloat(),
                case.name
                    .replace("Operation", "")
                    .replace("Case", "")
            )
        }
    }

    fun handleAction(action: PortfolioDetailsViewAction) {
        when (action) {
            is PortfolioDetailsViewAction.OnPortfolioDataProvided -> handleOnPortfolioDataProvided(
                action.portfolioItemListArgs
            )
        }
    }

    private fun handleOnPortfolioDataProvided(portfolioItemListArgs: PortfolioItemListArgs) {
        portfolioItemList = portfolioItemListArgs.portfolioItemList
        Log.e("onPortfolioDataProvided", "$portfolioItemList")
        showContent(portfolioItemList)
    }

    private fun showContent(portfolioItemList: List<PortfolioItem>) {
        uiState.value = PortfolioDetailsViewState.Content(mapToPieEntry(portfolioItemList))
    }
}

