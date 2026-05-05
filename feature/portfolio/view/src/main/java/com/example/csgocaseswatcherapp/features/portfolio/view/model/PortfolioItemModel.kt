package com.example.csgocaseswatcherapp.features.portfolio.view.model

data class PortfolioItemModel(
    val itemImage: String,
    val itemName: String,
    val totalValue: Double,
    val amount: Int,
    val price: Double,
    val profitLoss: Double,
    val profitLossPercent: Double
)

