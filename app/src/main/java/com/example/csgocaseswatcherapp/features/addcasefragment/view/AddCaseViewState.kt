package com.example.csgocaseswatcherapp.features.addcasefragment.view

import com.example.csgocaseswatcherapp.features.addcasefragment.view.entities.AddCaseModel

sealed class AddCaseViewState {

    data object Loading : AddCaseViewState()

    data object Error: AddCaseViewState()

    data class Content(
        val caseModel: AddCaseModel,
        val caseNameSearchQuery: String = "",
        val isAddCaseButtonActive: Boolean = false,
        val caseNameSuggestionList: List<String>
    ) : AddCaseViewState()
}