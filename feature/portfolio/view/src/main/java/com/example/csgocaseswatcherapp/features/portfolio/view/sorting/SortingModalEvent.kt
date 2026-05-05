package com.example.csgocaseswatcherapp.features.portfolio.view.sorting

import com.example.csgocaseswatcherapp.features.portfolio.view.sorting.model.SortState

sealed class SortingModalEvent {

    data class NavigateToPortfolioWithSelectedSortingMethod(
        val sortState: SortState
    ) : SortingModalEvent()
}