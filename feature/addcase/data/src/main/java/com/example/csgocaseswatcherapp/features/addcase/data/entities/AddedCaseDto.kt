package com.example.csgocaseswatcherapp.features.addcase.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class AddedCaseDto(
    val name: String,
    val amount: Int,
    val purchasePrice: Double
)