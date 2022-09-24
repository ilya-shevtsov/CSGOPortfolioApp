package com.example.csgocaseswatcherapp.screens.portfolio.view.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PortfolioItemListArgs(
    val portfolioItemList: List<PortfolioCaseItem>
): Parcelable
