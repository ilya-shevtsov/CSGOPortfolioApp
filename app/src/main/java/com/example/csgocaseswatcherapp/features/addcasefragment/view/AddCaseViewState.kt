package com.example.csgocaseswatcherapp.features.addcasefragment.view

import com.example.csgocaseswatcherapp.features.addcasefragment.view.entities.AddedCaseModel

sealed class AddCaseViewState {

    object Loading : AddCaseViewState()

    data class Content(
        val addedCase: AddedCaseModel
    ) : AddCaseViewState()
}