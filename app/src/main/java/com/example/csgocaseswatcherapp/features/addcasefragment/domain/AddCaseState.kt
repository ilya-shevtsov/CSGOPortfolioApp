package com.example.csgocaseswatcherapp.features.addcasefragment.domain


data class AddCaseState(
    val name: String,
    val amountField: AddCaseFieldData<AmountValidationResult>,
    val priceField: AddCaseFieldData<PriceValidationResult>,
    val caseNameSearchQuery: String = "",
    val nameSuggestionResult: NameSuggestionResult,
    val originalNameSuggestionList: List<String>
)

data class AddCaseFieldData<T>(
    val input: String,
    val result: T
)

sealed interface NameSuggestionResult {
    data class Success(val suggestionList: List<String>) : NameSuggestionResult
    data class Error(val errorMessage: String?) : NameSuggestionResult
    data object Loading : NameSuggestionResult
}



