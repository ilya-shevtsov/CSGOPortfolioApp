package com.example.csgocaseswatcherapp.presentation.view.fragments.addCaseFragment

import com.example.csgocaseswatcherapp.presentation.model.addcaseitem.AddedCaseModel

sealed class AddCaseViewState {

    object Loading : AddCaseViewState()

    data class AutoCompleteArray(
        val array: List<String>
    ) : AddCaseViewState()

    data class Content(
        val addedCase: AddedCaseModel
    ) : AddCaseViewState()
}