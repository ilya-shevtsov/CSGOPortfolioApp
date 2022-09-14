package com.example.csgocaseswatcherapp.presentation.view.fragments.portfolio

import com.example.csgocaseswatcherapp.presentation.model.addcaseitem.AddedCaseModel
import com.example.csgocaseswatcherapp.presentation.view.fragments.sortingbottomsheetfragment.SortingMethod

sealed class PortfolioViewAction {

    data class OnCaseAdded(
        val addedCase: AddedCaseModel
    ) : PortfolioViewAction()

    data class OnSortingMethodSelected(
        val sortingMethod:SortingMethod
    ): PortfolioViewAction()

    object OnAddCaseClicked : PortfolioViewAction()

    object OnSortClicked : PortfolioViewAction()

    object OnPortfolioDetailsClicked : PortfolioViewAction()

}