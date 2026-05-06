package com.example.csgocaseswatcherapp.features.addcase.domain

import com.example.csgocaseswatcherapp.features.addcase.domain.entities.AddCaseSuggestion


data class AddCaseState(
    val name: String,
    val amountField: AddCaseFieldData<AmountValidationResult>,
    val priceField: AddCaseFieldData<PriceValidationResult>,
    val caseNameSearchQuery: String = "",
    val nameSuggestionResult: NameSuggestionResult,
    val originalNameSuggestionList: List<AddCaseSuggestion>
) {
    companion object {
        fun initial(): AddCaseState {
            return AddCaseState(
                name = "",
                caseNameSearchQuery = "",
                nameSuggestionResult = NameSuggestionResult.Loading,
                originalNameSuggestionList = emptyList(),
                amountField = AddCaseFieldData(
                    input = "",
                    result = AmountValidationResult.Fail(AddCaseError.AMOUNT_EMPTY)
                ),
                priceField = AddCaseFieldData(
                    input = "",
                    result = PriceValidationResult.Fail(AddCaseError.PRICE_EMPTY)
                )
            )
        }
    }
}

data class AddCaseFieldData<T>(
    val input: String,
    val result: T
)

sealed interface NameSuggestionResult {
    data class Success(val suggestionList: List<AddCaseSuggestion>) : NameSuggestionResult
    data class Error(val errorMessage: String?) : NameSuggestionResult
    data object Loading : NameSuggestionResult
}



