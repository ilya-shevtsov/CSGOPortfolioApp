package com.example.csgocaseswatcherapp.features.portfolio.view.entities

import com.example.csgocaseswatcherapp.features.portfolio.domain.entities.PortfolioItem
import kotlinx.serialization.Serializable

@Serializable
data class PortfolioItemListArgs(
    val portfolioItemList: List<PortfolioItem>
)