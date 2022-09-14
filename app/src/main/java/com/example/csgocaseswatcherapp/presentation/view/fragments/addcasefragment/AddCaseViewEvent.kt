package com.example.csgocaseswatcherapp.presentation.view.fragments.addcasefragment

import com.example.csgocaseswatcherapp.presentation.model.addcaseitem.AddedCaseModel

sealed class AddCaseViewEvent {

    data class NavigateToPortfolioWithAddedCase(
        val addedCase: AddedCaseModel
    ) : AddCaseViewEvent()
}