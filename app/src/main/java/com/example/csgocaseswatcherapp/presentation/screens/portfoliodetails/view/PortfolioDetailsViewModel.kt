package com.example.csgocaseswatcherapp.presentation.screens.portfoliodetails.view

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.csgocaseswatcherapp.presentation.model.PortfolioItemListArgs
import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioCaseItem
import com.github.mikephil.charting.data.PieEntry
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class PortfolioDetailsViewModel : ViewModel() {

    var portfolioItemList: List<PortfolioCaseItem> = listOf()

    val uiState: MutableStateFlow<PortfolioDetailsViewState> =
        MutableStateFlow(value = PortfolioDetailsViewState.Loading)

    val uiEvent = MutableSharedFlow<PortfolioDetailsViewEvent>()

    private fun mapToPieEntry(PortfolioItemList: List<PortfolioCaseItem>): List<PieEntry> {
        return PortfolioItemList.map { case ->
            PieEntry(
                case.caseAmount.toFloat(),
                case.caseName
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

    private fun showContent(portfolioItemList: List<PortfolioCaseItem>) {
        uiState.value = PortfolioDetailsViewState.Content(mapToPieEntry(portfolioItemList))
    }
}

