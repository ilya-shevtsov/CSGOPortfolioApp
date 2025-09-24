package com.example.csgocaseswatcherapp.features.addcase.view

sealed class AddCaseAction {

    data object OnCreate : AddCaseAction()
    data class OnNameChanged(val name: String) : AddCaseAction()
    data class OnAmountChanged(val amount: String) : AddCaseAction()
    data class OnPriceChanged(val price: String) : AddCaseAction()
    data object OnAddCaseClicked : AddCaseAction()
    data class OnSuggestionClicked(val name: String) : AddCaseAction()


}