package com.example.csgocaseswatcherapp.features.addcasefragment.domain

import com.example.csgocaseswatcherapp.features.addcasefragment.view.entities.AddedCase

data class AddCaseState(
    val addedCaseData: AddedCase,
    val caseNameSearchQuery: String = "",
    val nameSuggestionResult: NameSuggestionResult,
    val originalNameSuggestionList: List<String>
)


sealed interface NameSuggestionResult {

    data class Success(val suggestionList: List<String>): NameSuggestionResult

    data class Error(val errorMessage:String?) : NameSuggestionResult

    data object Loading: NameSuggestionResult
}


