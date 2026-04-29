package com.example.csgocaseswatcherapp.features.portfolio.view

import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItemModel
import com.github.mikephil.charting.data.BarEntry

sealed class PortfolioViewState {

    data class Content(
        val portfolioBartEntryList: List<BarEntry>,
        val totalPortfolioValue: Double,
        val portfolioItemModelList:List<PortfolioItemModel>,
        val isSortingSheetVisible: Boolean = false
    ) : PortfolioViewState()

    data object Loading : PortfolioViewState()

    data object Error : PortfolioViewState()
}

