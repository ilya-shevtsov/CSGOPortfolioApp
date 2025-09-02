package com.example.csgocaseswatcherapp.features.sortingmodal.view

import com.example.csgocaseswatcherapp.features.sortingmodal.entities.SortingMethod

sealed class SortingModalEvent {

    data class NavigateToPortfolioWithSelectedSortingMethod(
        val sortingMethod: SortingMethod
    ) : SortingModalEvent()
}