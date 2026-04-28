package com.example.csgocaseswatcherapp.features.addcase.domain

import com.example.csgocaseswatcherapp.features.addcase.domain.entities.AddCaseSuggestion
import com.example.csgocaseswatcherapp.features.addcase.domain.entities.AddedCase

interface AddCaseRepository {
    fun sendAddedCase(addedCase: AddedCase)
    fun getSuggestionList(): List<AddCaseSuggestion>
}