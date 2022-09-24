package com.example.csgocaseswatcherapp.screens.addcasefragment.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class AddedCaseDto(
    val name: String,
    val amount: Int,
    val purchasePrice: Double
)
