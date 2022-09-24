package com.example.csgocaseswatcherapp.features.sortingbottomsheetfragment.view

sealed class SortingBottomSheetFragmentViewAction {

    data class OnSortingMethodSelected(
        val sortingMethod: SortingMethod
    ) : SortingBottomSheetFragmentViewAction()
}