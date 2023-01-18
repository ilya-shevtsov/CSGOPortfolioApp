package com.example.csgocaseswatcherapp.features.portfolio.view

import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItem
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioValueItem
import com.github.mikephil.charting.data.BarEntry

sealed class PortfolioViewState {

    data class Content(
        val portfolioItemList: List<PortfolioItem>,
        val portfolioValueList: List<PortfolioValueItem>,
        val portfolioBartEntryList: List<BarEntry>,
        val totalPortfolioValue: Double
    ) : PortfolioViewState()

    object Loading : PortfolioViewState()

    object Error : PortfolioViewState()
}

