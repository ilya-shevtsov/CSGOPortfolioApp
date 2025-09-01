package com.example.csgocaseswatcherapp.features.sortingmodal.view

sealed class SortingModalAction {

    data class OnSortingMethodSelected(
        val sortingMethod: SortingMethod
    ) : SortingModalAction()
}