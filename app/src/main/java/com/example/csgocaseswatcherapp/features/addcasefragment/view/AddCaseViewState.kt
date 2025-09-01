package com.example.csgocaseswatcherapp.features.addcasefragment.view

import com.example.csgocaseswatcherapp.features.addcasefragment.view.entities.AddedCaseModel

sealed class AddCaseViewState {

    data object Loading : AddCaseViewState()

    data class Content(
        val caseName: String,
        val amount: String,
        val price: String,
        val caseNameSearchQuery: String = "",
        val isAddCaseButtonActive: Boolean = false,
        val caseNameSuggestionList: List<String>
    ) : AddCaseViewState()
}