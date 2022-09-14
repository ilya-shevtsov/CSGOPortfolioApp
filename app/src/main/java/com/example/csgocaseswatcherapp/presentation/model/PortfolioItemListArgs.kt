package com.example.csgocaseswatcherapp.presentation.model

import android.os.Parcelable
import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioCaseItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PortfolioItemListArgs(
    val portfolioItemList: List<PortfolioCaseItem>
): Parcelable
