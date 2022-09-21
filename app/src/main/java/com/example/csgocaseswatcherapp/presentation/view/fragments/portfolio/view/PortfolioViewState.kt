package com.example.csgocaseswatcherapp.presentation.view.fragments.portfolio.view

import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioCaseItem
import com.example.csgocaseswatcherapp.presentation.model.portfoliodetailsvalueitem.PortfolioValueItem
import com.github.mikephil.charting.data.BarEntry

sealed class PortfolioViewState {

    data class Content(
        val portfolioItemList: List<PortfolioCaseItem>,
        val portfolioValueList: List<PortfolioValueItem>,
        val portfolioBartEntryList: List<BarEntry>,
        val totalPortfolioValue: Double
    ) : PortfolioViewState()

    object Loading : PortfolioViewState()

    object Error : PortfolioViewState()
}

