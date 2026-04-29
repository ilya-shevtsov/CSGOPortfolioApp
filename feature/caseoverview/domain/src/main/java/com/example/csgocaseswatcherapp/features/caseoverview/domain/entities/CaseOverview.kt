package com.example.csgocaseswatcherapp.features.caseoverview.domain.entities

data class CaseOverview(
    val name: String,
    val lowestPrice: Double,
    val volume: Int,
    val medianPrice: Double,
    val imageUrl: String,
    val releaseDate: String,
    val dropStatus: String,
    val description: String
)