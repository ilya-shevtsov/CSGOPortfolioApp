package com.example.csgocaseswatcherapp.features.addcasefragment.domain

data class AddCaseState(
    val name: String,
    val amount: Int,
    val purchasePrice: Double,
    val caseNameSearchQuery: String = "",
    val nameSuggestionResult: NameSuggestionResult,
    val originalNameSuggestionList: List<String>
)


sealed interface NameSuggestionResult {

    data class Success(val suggestionList: List<String>) : NameSuggestionResult

    data class Error(val errorMessage: String?) : NameSuggestionResult

    data object Loading : NameSuggestionResult
}



