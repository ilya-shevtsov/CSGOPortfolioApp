package com.example.csgocaseswatcherapp.features.caseoverview.data.entities


import kotlinx.serialization.Serializable

@Serializable
data class CaseDto(
    val name: String,
    val releaseDate: String,
    val dropStatus: String,
    val lowestPrice: Double,
    val volume: Int,
    val medianPrice: Double,
    val imageUrl: String,
    val description: String
)