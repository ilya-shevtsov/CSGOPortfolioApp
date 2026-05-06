package com.example.csgocaseswatcherapp.features.portfolio.view.sorting

import com.example.csgocaseswatcherapp.features.portfolio.domain.model.PortfolioSortType

sealed class SortingModalEvent {

    data class NavigateToPortfolioWithSelectedSortingMethod(
        val sortType: PortfolioSortType
    ) : SortingModalEvent()
}