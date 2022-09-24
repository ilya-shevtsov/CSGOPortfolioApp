package com.example.csgocaseswatcherapp.features.addcasefragment.view

import com.example.csgocaseswatcherapp.features.addcasefragment.view.entities.AddedCaseModel

sealed class AddCaseViewAction {

    data class OnCaseAddedClicked(
        val addedCase: AddedCaseModel
    ) : AddCaseViewAction()
}