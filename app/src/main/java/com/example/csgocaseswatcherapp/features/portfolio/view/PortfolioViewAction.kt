package com.example.csgocaseswatcherapp.features.portfolio.view

import com.example.csgocaseswatcherapp.features.addcasefragment.view.entities.AddedCase
import com.example.csgocaseswatcherapp.features.sortingmodal.entities.SortState

sealed class PortfolioViewAction {

    data object OnCreate: PortfolioViewAction()


    data object OnCaseAdded: PortfolioViewAction()

    data class OnSortingMethodSelected(
        val sortState: SortState
    ): PortfolioViewAction()

    data object OnAddCaseClicked : PortfolioViewAction()

    data object OnSortClicked : PortfolioViewAction()

    data object OnPortfolioDetailsClicked : PortfolioViewAction()

    data object HideSortingModal : PortfolioViewAction()
}