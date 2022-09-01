package com.example.csgocaseswatcherapp.data.model.addedcase

import kotlinx.serialization.Serializable

@Serializable
data class AddedCaseDto(
    val name: String,
    val amount: Int,
    val purchasePrice: Double
)
