package com.example.csgocaseswatcherapp.screens.caseoverview.domain.entities

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