package com.example.csgocaseswatcherapp.presentation.screens.portfolio.view

import com.example.csgocaseswatcherapp.presentation.screens.addcasefragment.view.entities.AddedCaseModel
import com.example.csgocaseswatcherapp.presentation.screens.sortingbottomsheetfragment.view.SortingMethod

sealed class PortfolioViewAction {

    data class OnCaseAdded(
        val addedCase: AddedCaseModel
    ) : PortfolioViewAction()

    data class OnSortingMethodSelected(
        val sortingMethod: SortingMethod
    ): PortfolioViewAction()

    object OnAddCaseClicked : PortfolioViewAction()

    object OnSortClicked : PortfolioViewAction()

    object OnPortfolioDetailsClicked : PortfolioViewAction()
}