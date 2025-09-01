package com.example.csgocaseswatcherapp.features.sortingmodal.view

sealed class SortingModalEvent {

    data class NavigateToPortfolioWithSelectedSortingMethod(
        val sortingMethod: SortingMethod
    ) : SortingModalEvent()
}