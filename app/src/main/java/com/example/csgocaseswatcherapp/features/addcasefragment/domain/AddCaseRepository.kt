package com.example.csgocaseswatcherapp.features.addcasefragment.domain

import com.example.csgocaseswatcherapp.features.addcasefragment.view.entities.AddedCaseModel

interface AddCaseRepository {
    fun sendAddedCase(addedCase: AddedCaseModel)
    fun getSuggestionList(): List<String>
}