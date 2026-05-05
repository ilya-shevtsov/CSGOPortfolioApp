package com.example.csgocaseswatcherapp.features.portfolio.view.sorting

import com.example.csgocaseswatcherapp.features.portfolio.view.sorting.model.SortState

sealed class SortingModalAction {

    data class OnSortingMethodSelected(
        val sortState: SortState
    ) : SortingModalAction()
}