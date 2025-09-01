package com.example.csgocaseswatcherapp.features.addcasefragment.view

import com.example.csgocaseswatcherapp.features.addcasefragment.view.entities.AddedCaseModel

sealed class AddCaseViewAction {

    data object OnCaseAddedClicked : AddCaseViewAction()


    data class OnNameChanged(val name: String) : AddCaseViewAction()
    data class onAmountChanged(val amount: String) : AddCaseViewAction()
    data class onPriceChanged(val price: String) : AddCaseViewAction()
    data object onAddCaseClicked : AddCaseViewAction()
    data class onSuggestionClicked(val name: String) : AddCaseViewAction()


}