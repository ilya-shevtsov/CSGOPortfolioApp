package com.example.csgocaseswatcherapp.features.addcasefragment.domain

import com.example.csgocaseswatcherapp.features.addcasefragment.view.entities.AddedCase

interface AddCaseRepository {
    fun sendAddedCase(addedCase: AddedCase)
    fun getSuggestionList(): List<String>
}