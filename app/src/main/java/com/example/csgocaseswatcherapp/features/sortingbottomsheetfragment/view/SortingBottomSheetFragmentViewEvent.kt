package com.example.csgocaseswatcherapp.features.sortingbottomsheetfragment.view

sealed class SortingBottomSheetFragmentViewEvent {

    data class NavigateToPortfolioWithSelectedSortingMethod(
        val sortingMethod: SortingMethod
    ) : SortingBottomSheetFragmentViewEvent()
}