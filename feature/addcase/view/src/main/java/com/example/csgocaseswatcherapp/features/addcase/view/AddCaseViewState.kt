package com.example.csgocaseswatcherapp.features.addcase.view

import androidx.annotation.StringRes
import com.example.csgocaseswatcherapp.features.addcase.domain.entities.AddCaseSuggestion

sealed class AddCaseViewState {
    data object Loading : AddCaseViewState()
    data object Error : AddCaseViewState()

    data class Content(
        val name: String,
        val amount: String,
        val price: String,
        @param:StringRes val amountError: Int? = null,
        @param:StringRes val priceError: Int? = null,
        @param:StringRes val nameError: Int? = null,
        val caseNameSearchQuery: String = "",
        val isAddCaseButtonActive: Boolean = false,
        val caseNameSuggestionList: List<AddCaseSuggestion>
    ) : AddCaseViewState()
}