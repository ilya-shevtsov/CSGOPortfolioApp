package com.example.csgocaseswatcherapp.features.addcasefragment.view

sealed class AddCaseViewAction {

    data object OnCreate : AddCaseViewAction()
    data class OnNameChanged(val name: String) : AddCaseViewAction()
    data class OnAmountChanged(val amount: String) : AddCaseViewAction()
    data class OnPriceChanged(val price: String) : AddCaseViewAction()
    data object OnAddCaseClicked : AddCaseViewAction()
    data class OnSuggestionClicked(val name: String) : AddCaseViewAction()


}