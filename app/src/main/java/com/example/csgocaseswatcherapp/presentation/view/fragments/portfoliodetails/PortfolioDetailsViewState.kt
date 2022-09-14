package com.example.csgocaseswatcherapp.presentation.view.fragments.portfoliodetails

import com.github.mikephil.charting.data.PieEntry

sealed class PortfolioDetailsViewState {

    data class Content(
        val portfolioPietEntryList: List<PieEntry>,
    ) : PortfolioDetailsViewState()

    object Loading : PortfolioDetailsViewState()

    object Error : PortfolioDetailsViewState()
}