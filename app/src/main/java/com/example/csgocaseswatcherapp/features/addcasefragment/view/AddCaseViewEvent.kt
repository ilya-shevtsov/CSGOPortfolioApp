package com.example.csgocaseswatcherapp.features.addcasefragment.view

import com.example.csgocaseswatcherapp.features.addcasefragment.view.entities.AddedCase

sealed class AddCaseViewEvent {

    data class NavigateToPortfolioWithAddedCase(
        val addedCase: AddedCase
    ) : AddCaseViewEvent()

    data class ShowValidationError(val message:String): AddCaseViewEvent()

}