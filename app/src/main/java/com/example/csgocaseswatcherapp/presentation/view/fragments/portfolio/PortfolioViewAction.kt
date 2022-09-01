package com.example.csgocaseswatcherapp.presentation.view.fragments.portfolio

import com.example.csgocaseswatcherapp.presentation.model.addcaseitem.AddedCaseModel

sealed class PortfolioViewAction {

    data class OnCaseAdded(
        val addedCase: AddedCaseModel
    ) : PortfolioViewAction()

    object OnAddCaseClicked : PortfolioViewAction()

    object OnCaseNameSortClicked : PortfolioViewAction()

}