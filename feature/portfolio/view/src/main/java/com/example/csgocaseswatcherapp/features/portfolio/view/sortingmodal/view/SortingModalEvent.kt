package com.example.csgocaseswatcherapp.features.portfolio.view.sortingmodal.view

import com.example.csgocaseswatcherapp.features.portfolio.view.sortingmodal.entities.SortState

sealed class SortingModalEvent {

    data class NavigateToPortfolioWithSelectedSortingMethod(
        val sortState: SortState
    ) : SortingModalEvent()
}