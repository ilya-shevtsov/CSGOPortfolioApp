package com.example.csgocaseswatcherapp.features.portfolio.view

import com.example.csgocaseswatcherapp.features.portfolio.domain.entities.PortfolioItem
import com.example.csgocaseswatcherapp.features.portfolio.view.model.PortfolioValueItem
import com.example.csgocaseswatcherapp.features.portfolio.view.sorting.model.SortState
import com.github.mikephil.charting.data.BarEntry

data class PortfolioState(
    val portfolioItemListResult:PortfolioItemListResult,
    val portfolioValueList: List<PortfolioValueItem>,
    val portfolioBartEntryList: List<BarEntry>,
    val totalPortfolioValue: Double,
    val isSortingSheetVisible: Boolean,
    val sortState: SortState
)


sealed interface PortfolioItemListResult {
    data class Success(val portfolioItemList: List<PortfolioItem>) : PortfolioItemListResult
    data class Error(val errorMessage: String?) : PortfolioItemListResult
    data object Loading : PortfolioItemListResult
}