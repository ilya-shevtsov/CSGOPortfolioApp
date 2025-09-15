package com.example.csgocaseswatcherapp.features.addcasefragment.view

sealed class AddCaseViewState {

    data object Loading : AddCaseViewState()

    data object Error: AddCaseViewState()

    data class Content(
        val name: String,
        val amount:String,
        val price: String,
        val amountError: String? = null,
        val priceError: String? = null,
        val nameError: String? = null,
        val caseNameSearchQuery: String = "",
        val isAddCaseButtonActive: Boolean = false,
        val caseNameSuggestionList: List<String>
    ) : AddCaseViewState()
}