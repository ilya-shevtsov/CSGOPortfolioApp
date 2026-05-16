package com.example.csgocaseswatcherapp.features.portfolio.view

import com.example.csgocaseswatcherapp.features.portfolio.view.model.PortfolioBarEntryModel
import com.example.csgocaseswatcherapp.features.portfolio.view.model.PortfolioItemModel
import kotlinx.collections.immutable.PersistentList

sealed class PortfolioViewState {

    data class Content(
        val portfolioBartEntryList: PersistentList<PortfolioBarEntryModel>,
        val totalPortfolioValue: Double,
        val portfolioItemModelList: PersistentList<PortfolioItemModel>,
        val isSortingSheetVisible: Boolean
    ) : PortfolioViewState()

    data object Loading : PortfolioViewState()

    data object Error : PortfolioViewState()
}

