package com.example.csgocaseswatcherapp.features.addcasefragment.domain

import com.example.csgocaseswatcherapp.features.addcasefragment.data.entities.AddCaseSuggestion


data class AddCaseState(
    val name: String,
    val amountField: AddCaseFieldData<AmountValidationResult>,
    val priceField: AddCaseFieldData<PriceValidationResult>,
    val caseNameSearchQuery: String = "",
    val nameSuggestionResult: NameSuggestionResult,
    val originalNameSuggestionList: List<AddCaseSuggestion>
)

data class AddCaseFieldData<T>(
    val input: String,
    val result: T
)

sealed interface NameSuggestionResult {
    data class Success(val suggestionList: List<AddCaseSuggestion>) : NameSuggestionResult
    data class Error(val errorMessage: String?) : NameSuggestionResult
    data object Loading : NameSuggestionResult
}



