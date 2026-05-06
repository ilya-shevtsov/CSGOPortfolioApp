package com.example.csgocaseswatcherapp.features.addcase.view

import androidx.annotation.StringRes
import com.example.csgocaseswatcherapp.features.addcase.domain.AddCaseState
import com.example.csgocaseswatcherapp.features.addcase.domain.AmountValidationResult
import com.example.csgocaseswatcherapp.features.addcase.domain.NameSuggestionResult
import com.example.csgocaseswatcherapp.features.addcase.domain.PriceValidationResult
import com.example.csgocaseswatcherapp.features.addcase.domain.entities.AddCaseSuggestion
import com.example.csgocaseswatcherapp.features.addcase.domain.usecases.ValidateCaseNameUseCase
import javax.inject.Inject

class AddCaseViewStateMapper @Inject constructor(
    private val validateCaseNameUseCase: ValidateCaseNameUseCase
) {

    fun map(state: AddCaseState): AddCaseViewState {
        return when (val result = state.nameSuggestionResult) {
            is NameSuggestionResult.Error -> AddCaseViewState.Error
            is NameSuggestionResult.Loading -> AddCaseViewState.Loading
            is NameSuggestionResult.Success -> mapContent(
                state = state,
                result = result
            )
        }
    }

    private fun mapContent(
        state: AddCaseState,
        result: NameSuggestionResult.Success,
    ): AddCaseViewState.Content {

        val filteredSuggestions = filterSuggestions(
            searchQuery = state.caseNameSearchQuery,
            suggestions = result.suggestionList
        )

        val allowedNames = state.originalNameSuggestionList
            .map { it.name }
            .toSet()

        val nameError = validateCaseNameUseCase(
            name = state.name,
            allowedNames = allowedNames
        )?.resId

        val amountError = state.amountField.result.toErrorResOrNull()
        val priceError = state.priceField.result.toErrorResOrNull()

        val addCaseButtonIsActive = nameError == null && amountError == null && priceError == null

        return AddCaseViewState.Content(
            name = state.name,
            amount = state.amountField.input,
            price = state.priceField.input,
            caseNameSearchQuery = state.caseNameSearchQuery,
            isAddCaseButtonActive = addCaseButtonIsActive,
            caseNameSuggestionList = filteredSuggestions,
            nameError = nameError,
            amountError = amountError,
            priceError = priceError
        )
    }

    private fun filterSuggestions(
        searchQuery: String,
        suggestions: List<AddCaseSuggestion>
    ): List<AddCaseSuggestion> {
        return if (searchQuery.isBlank()) {
            emptyList()
        } else {
            suggestions
                .filter { it.name.contains(searchQuery, ignoreCase = true) }
                .take(MAX_SUGGESTIONS)
        }
    }

    @StringRes
    private fun AmountValidationResult.toErrorResOrNull(): Int? = when (this) {
        is AmountValidationResult.Success -> null
        is AmountValidationResult.Fail -> this.error.resId
    }

    @StringRes
    private fun PriceValidationResult.toErrorResOrNull(): Int? = when (this) {
        is PriceValidationResult.Success -> null
        is PriceValidationResult.Fail -> this.error.resId
    }

    private companion object {
        const val MAX_SUGGESTIONS = 8
    }
}

