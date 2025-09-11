package com.example.csgocaseswatcherapp.features.sortingmodal.view

import com.example.csgocaseswatcherapp.features.sortingmodal.entities.SortState

sealed class SortingModalAction {

    data class OnSortingMethodSelected(
        val sortState: SortState
    ) : SortingModalAction()
}