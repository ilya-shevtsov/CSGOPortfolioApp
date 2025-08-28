package com.example.csgocaseswatcherapp.features.portfolio.view

import com.example.csgocaseswatcherapp.features.addcasefragment.view.entities.AddedCaseModel
import com.example.csgocaseswatcherapp.features.sortingmodal.view.SortingMethod

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