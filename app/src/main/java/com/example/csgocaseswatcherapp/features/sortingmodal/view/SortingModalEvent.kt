package com.example.csgocaseswatcherapp.features.sortingmodal.view

import com.example.csgocaseswatcherapp.features.sortingmodal.entities.SortState

sealed class SortingModalEvent {

    data class NavigateToPortfolioWithSelectedSortingMethod(
        val sortState: SortState
    ) : SortingModalEvent()
}