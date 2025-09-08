package com.example.csgocaseswatcherapp.features.caseoverviewdetails.view

sealed class CaseDetailsViewState {

    data object Loading : CaseDetailsViewState()

    data class Content(
        val caseName: String,
        val lowestPrice: String,
        val volume: String,
        val medianPrice: String,
        val imageUrl: String,
        val releaseDate: String,
        val dropStatus: String,
        val description: String
    ): CaseDetailsViewState()
}

