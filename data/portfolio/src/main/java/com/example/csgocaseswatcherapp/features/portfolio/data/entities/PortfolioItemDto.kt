package com.example.csgocaseswatcherapp.features.portfolio.data.entities

data class PortfolioItemDto(
    val name: String,
    val amount: Int,
    val purchasePrice: Double,
    val overallValue: Double,
    val profitLoss: Double,
    val imageUrl: String
)