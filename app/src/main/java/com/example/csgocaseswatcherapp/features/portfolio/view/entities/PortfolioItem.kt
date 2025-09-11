package com.example.csgocaseswatcherapp.features.portfolio.view.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PortfolioItem(
    val image: String,
    val name: String,
    val amount: Int,
    val price: Double,
    val overallValue: Double,
    val profitLoss: Double,
) : Parcelable




