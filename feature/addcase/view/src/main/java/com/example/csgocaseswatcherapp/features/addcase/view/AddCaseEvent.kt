package com.example.csgocaseswatcherapp.features.addcase.view

import com.example.csgocaseswatcherapp.features.addcase.domain.AddCaseError

sealed class AddCaseEvent {

    data object NavigateToPortfolioWithAddedCase : AddCaseEvent()

    data class ShowValidationError(val error: AddCaseError) : AddCaseEvent()

}