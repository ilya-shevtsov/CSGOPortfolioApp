package com.example.csgocaseswatcherapp.presentation.screens.portfolio.view.entities

import android.os.Parcelable
import com.example.csgocaseswatcherapp.presentation.screens.portfolio.view.entities.PortfolioCaseItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PortfolioItemListArgs(
    val portfolioItemList: List<PortfolioCaseItem>
): Parcelable
