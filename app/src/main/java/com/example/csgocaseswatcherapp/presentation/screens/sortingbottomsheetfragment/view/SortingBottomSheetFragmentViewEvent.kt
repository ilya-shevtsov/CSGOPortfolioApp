package com.example.csgocaseswatcherapp.presentation.screens.sortingbottomsheetfragment.view

sealed class SortingBottomSheetFragmentViewEvent {

    data class NavigateToPortfolioWithSelectedSortingMethod(
        val sortingMethod: SortingMethod
    ) : SortingBottomSheetFragmentViewEvent()
}