package com.example.csgocaseswatcherapp.features.portfolio.view.sorting

import com.example.csgocaseswatcherapp.features.portfolio.domain.model.PortfolioSortType

sealed class SortingModalAction {

    data class OnSortingMethodSelected(
        val sortType: PortfolioSortType
    ) : SortingModalAction()
}