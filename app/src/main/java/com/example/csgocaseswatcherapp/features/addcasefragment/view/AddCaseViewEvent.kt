package com.example.csgocaseswatcherapp.features.addcasefragment.view

sealed class AddCaseViewEvent {

    data object NavigateToPortfolioWithAddedCase: AddCaseViewEvent()

    data class ShowValidationError(val message:String): AddCaseViewEvent()

}