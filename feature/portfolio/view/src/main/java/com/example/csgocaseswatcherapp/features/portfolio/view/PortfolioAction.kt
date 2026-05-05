package com.example.csgocaseswatcherapp.features.portfolio.view

import com.example.csgocaseswatcherapp.features.portfolio.view.sorting.model.SortState


sealed class PortfolioAction {

    data object OnCreate : PortfolioAction()


    data object OnCaseAdded : PortfolioAction()

    data class OnSortingMethodSelected(
        val sortState: SortState
    ) : PortfolioAction()

    data object OnAddCaseClicked : PortfolioAction()

    data object OnSortClicked : PortfolioAction()

    data object OnPortfolioDetailsClicked : PortfolioAction()

    data object HideSortingModal : PortfolioAction()
}