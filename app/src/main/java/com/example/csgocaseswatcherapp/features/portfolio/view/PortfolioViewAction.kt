package com.example.csgocaseswatcherapp.features.portfolio.view

import com.example.csgocaseswatcherapp.features.addcasefragment.view.entities.AddedCase
import com.example.csgocaseswatcherapp.features.sortingmodal.entities.SortingMethod

sealed class PortfolioViewAction {

    data class OnCaseAdded(
        val addedCase: AddedCase
    ) : PortfolioViewAction()

    data class OnSortingMethodSelected(
        val sortingMethod: SortingMethod
    ): PortfolioViewAction()

    data object OnAddCaseClicked : PortfolioViewAction()

    data object OnSortClicked : PortfolioViewAction()

    data object OnPortfolioDetailsClicked : PortfolioViewAction()

    data object HideSortingModal : PortfolioViewAction()
}