package com.example.csgocaseswatcherapp.screens.sortingbottomsheetfragment.view

sealed class SortingBottomSheetFragmentViewAction {

    data class OnSortingMethodSelected(
        val sortingMethod: SortingMethod
    ) : SortingBottomSheetFragmentViewAction()
}