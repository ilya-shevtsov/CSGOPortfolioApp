package com.example.csgocaseswatcherapp.presentation.screens.sortingbottomsheetfragment.view

sealed class SortingBottomSheetFragmentViewAction {

    data class OnSortingMethodSelected(
        val sortingMethod: SortingMethod
    ) : SortingBottomSheetFragmentViewAction()
}