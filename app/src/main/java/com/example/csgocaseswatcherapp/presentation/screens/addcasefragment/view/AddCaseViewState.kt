package com.example.csgocaseswatcherapp.presentation.screens.addcasefragment.view

import com.example.csgocaseswatcherapp.presentation.screens.addcasefragment.view.entities.AddedCaseModel

sealed class AddCaseViewState {

    object Loading : AddCaseViewState()

    data class Content(
        val addedCase: AddedCaseModel
    ) : AddCaseViewState()
}