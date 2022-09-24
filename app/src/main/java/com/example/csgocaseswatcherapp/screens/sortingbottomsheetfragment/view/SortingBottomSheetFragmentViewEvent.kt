package com.example.csgocaseswatcherapp.screens.sortingbottomsheetfragment.view

sealed class SortingBottomSheetFragmentViewEvent {

    data class NavigateToPortfolioWithSelectedSortingMethod(
        val sortingMethod: SortingMethod
    ) : SortingBottomSheetFragmentViewEvent()
}