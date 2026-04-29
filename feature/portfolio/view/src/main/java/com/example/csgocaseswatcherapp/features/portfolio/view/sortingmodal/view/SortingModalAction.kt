package com.example.csgocaseswatcherapp.features.portfolio.view.sortingmodal.view

import com.example.csgocaseswatcherapp.features.portfolio.view.sortingmodal.entities.SortState

sealed class SortingModalAction {

    data class OnSortingMethodSelected(
        val sortState: SortState
    ) : SortingModalAction()
}