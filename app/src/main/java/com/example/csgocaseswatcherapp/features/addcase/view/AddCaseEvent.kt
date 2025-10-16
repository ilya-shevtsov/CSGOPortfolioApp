package com.example.csgocaseswatcherapp.features.addcase.view

sealed class AddCaseEvent {

    data object NavigateToPortfolioWithAddedCase : AddCaseEvent()

    data class ShowValidationError(val message: String) : AddCaseEvent()

}