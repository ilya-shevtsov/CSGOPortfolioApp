package com.example.csgocaseswatcherapp.presentation.view.fragments.sortingbottomsheetfragment

sealed class SortingBottomSheetFragmentViewEvent {

    data class NavigateToPortfolioWithSelectedSortingMethod(
        val sortingMethod: SortingMethod
    ) : SortingBottomSheetFragmentViewEvent()
}