package com.example.csgocaseswatcherapp.presentation.view.fragments.portfolio

import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioGroupieItem
import com.example.csgocaseswatcherapp.presentation.model.portfoliodetailsvalueitem.PortfolioValueItem
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieEntry

sealed class PortfolioViewState {

    data class Content(
        val portfolioItemList: List<PortfolioGroupieItem>,
        val portfolioValueList: List<PortfolioValueItem>,
        val portfolioBartEntryList: List<BarEntry>,
        val totalPortfolioValue: Double
    ) : PortfolioViewState()

    object Loading : PortfolioViewState()

    object Error : PortfolioViewState()
}

