package com.example.csgocaseswatcherapp.presentation.view.fragments.portfolio

import com.example.csgocaseswatcherapp.presentation.model.addcaseitem.AddCaseItem

sealed class PortfolioViewAction {

    data class OnCaseAdded(
        val addedCase: AddCaseItem
    ) : PortfolioViewAction()

    object OnAddCaseClicked : PortfolioViewAction()

}