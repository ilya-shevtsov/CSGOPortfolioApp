package com.example.csgocaseswatcherapp.features.portfolio.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class PortfolioItem(
    val image: String,
    val name: String,
    val amount: Int,
    val price: Double,
    val overallValue: Double,
    val profitLoss: Double,
)