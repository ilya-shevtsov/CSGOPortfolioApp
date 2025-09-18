package com.example.csgocaseswatcherapp.features.addcasefragment.view

import androidx.annotation.StringRes

sealed class AddCaseViewState {
    data object Loading : AddCaseViewState()
    data object Error : AddCaseViewState()

    data class Content(
        val name: String,
        val amount: String,
        val price: String,
        @StringRes val amountError: Int? = null,
        @StringRes val priceError: Int? = null,
        @StringRes val nameError: Int? = null,
        val caseNameSearchQuery: String = "",
        val isAddCaseButtonActive: Boolean = false,
        val caseNameSuggestionList: List<String>
    ) : AddCaseViewState()
}