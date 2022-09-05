package com.example.csgocaseswatcherapp.presentation.view.fragments.portfoliodetails

import com.example.csgocaseswatcherapp.presentation.model.portfoliodetailsvalueitem.PortfolioValueItem
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieEntry

sealed class PortfolioDetailsViewState {

    data class Content(
        val portfolioValueList: List<PortfolioValueItem>,
        val portfolioBartEntryList: List<BarEntry>,
    ) : PortfolioDetailsViewState()

    object Loading : PortfolioDetailsViewState()

    object Error : PortfolioDetailsViewState()
}