package com.example.csgocaseswatcherapp.features.addcase.domain

import com.example.csgocaseswatcherapp.features.addcase.data.entities.AddCaseSuggestion
import com.example.csgocaseswatcherapp.features.addcase.view.entities.AddedCase

interface AddCaseRepository {
    fun sendAddedCase(addedCase: AddedCase)
    fun getSuggestionList(): List<AddCaseSuggestion>
}