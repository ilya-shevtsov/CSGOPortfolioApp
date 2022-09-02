package com.example.csgocaseswatcherapp.data.model.portfolioitem

data class PortfolioItemDto(
    val name: String,
    val amount: Int,
    val purchasePrice: Double,
    val overallValue: Double,
    val profitLoss: Double,
    val imageUrl: String
)