package com.example.csgocaseswatcherapp.screens.addcasefragment.view

import com.example.csgocaseswatcherapp.screens.addcasefragment.view.entities.AddedCaseModel

sealed class AddCaseViewAction {

    data class OnCaseAddedClicked(
        val addedCase: AddedCaseModel
    ) : AddCaseViewAction()
}