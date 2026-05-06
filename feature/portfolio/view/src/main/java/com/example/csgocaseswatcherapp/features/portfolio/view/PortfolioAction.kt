package com.example.csgocaseswatcherapp.features.portfolio.view

import com.example.csgocaseswatcherapp.features.portfolio.domain.model.PortfolioSortType


sealed class PortfolioAction {

    data object OnCreate : PortfolioAction()


    data object OnCaseAdded : PortfolioAction()

    data class OnSortingMethodSelected(
        val sortType: PortfolioSortType
    ) : PortfolioAction()

    data object OnAddCaseClicked : PortfolioAction()

    data object OnSortClicked : PortfolioAction()

    data object OnPortfolioDetailsClicked : PortfolioAction()

    data object HideSortingModal : PortfolioAction()
}