package com.example.csgocaseswatcherapp.presentation.screens.addcasefragment.view

import com.example.csgocaseswatcherapp.presentation.model.addcaseitem.AddedCaseModel

sealed class AddCaseViewEvent {

    data class NavigateToPortfolioWithAddedCase(
        val addedCase: AddedCaseModel
    ) : AddCaseViewEvent()
}