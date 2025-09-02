package com.example.csgocaseswatcherapp.features.sortingmodal.view

import com.example.csgocaseswatcherapp.features.sortingmodal.entities.SortingMethod

sealed class SortingModalAction {

    data class OnSortingMethodSelected(
        val sortingMethod: SortingMethod
    ) : SortingModalAction()
}