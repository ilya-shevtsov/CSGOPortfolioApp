package com.example.csgocaseswatcherapp.screens.addcasefragment.view

import com.example.csgocaseswatcherapp.screens.addcasefragment.view.entities.AddedCaseModel

sealed class AddCaseViewEvent {

    data class NavigateToPortfolioWithAddedCase(
        val addedCase: AddedCaseModel
    ) : AddCaseViewEvent()
}