package com.example.csgocaseswatcherapp.features.addcase.domain.entities

import java.io.Serializable

data class AddedCase(
    val name: String,
    val amount: Int,
    val purchasePrice: Double
) : Serializable