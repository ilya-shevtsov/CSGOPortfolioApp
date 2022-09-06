package com.example.csgocaseswatcherapp.presentation.view.fragments.addcasefragment

import com.example.csgocaseswatcherapp.presentation.model.addcaseitem.AddedCaseModel

sealed class AddCaseViewAction {

    data class OnCaseAddedClicked(
        val addedCase: AddedCaseModel
    ) : AddCaseViewAction()

}