package com.example.csgocaseswatcherapp.presentation.view.fragments.portfolio

import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioGroupieItem
import com.github.mikephil.charting.data.PieEntry

sealed class PortfolioViewState {

    data class Content(
        val portfolioItemList: List<PortfolioGroupieItem>,
        val portfolioPietEntryList: List<PieEntry>,
        val totalPortfolioValue: Double
    ) : PortfolioViewState()

    object Loading : PortfolioViewState()

    object Error : PortfolioViewState()
}

