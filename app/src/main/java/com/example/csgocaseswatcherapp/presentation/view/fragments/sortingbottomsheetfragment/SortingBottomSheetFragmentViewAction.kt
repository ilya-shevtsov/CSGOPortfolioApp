package com.example.csgocaseswatcherapp.presentation.view.fragments.sortingbottomsheetfragment

sealed class SortingBottomSheetFragmentViewAction {

    data class OnSortingMethodSelected(
        val sortingMethod: SortingMethod
    ) : SortingBottomSheetFragmentViewAction()
}