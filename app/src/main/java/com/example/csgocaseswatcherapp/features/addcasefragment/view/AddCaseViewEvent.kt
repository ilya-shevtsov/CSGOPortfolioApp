package com.example.csgocaseswatcherapp.features.addcasefragment.view

import com.example.csgocaseswatcherapp.features.addcasefragment.view.entities.AddedCaseModel

sealed class AddCaseViewEvent {

    data class NavigateToPortfolioWithAddedCase(
        val addedCase: AddedCaseModel
    ) : AddCaseViewEvent()
}