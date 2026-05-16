package com.example.csgocaseswatcherapp.features.caseoverview.view.model


import kotlinx.serialization.Serializable


@Serializable
data class CaseOverviewModel(
    val caseName: String,
    val lowestPrice: Double,
    val volume: Int,
    val medianPrice: Double,
    val imageUrl: String,
    val releaseDate: String,
    val dropStatus: String,
    val description: String
)
