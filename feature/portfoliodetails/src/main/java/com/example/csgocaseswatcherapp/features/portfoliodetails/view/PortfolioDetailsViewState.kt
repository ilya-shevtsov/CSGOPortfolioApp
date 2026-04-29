package com.example.csgocaseswatcherapp.features.portfoliodetails.view

import com.github.mikephil.charting.data.PieEntry

sealed class PortfolioDetailsViewState {

    data class Content(
        val portfolioPietEntryList: List<PieEntry>,
    ) : PortfolioDetailsViewState()

    data object Loading : PortfolioDetailsViewState()

    data object Error : PortfolioDetailsViewState()
}