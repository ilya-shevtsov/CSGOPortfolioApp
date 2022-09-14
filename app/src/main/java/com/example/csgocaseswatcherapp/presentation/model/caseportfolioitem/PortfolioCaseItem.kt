package com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class PortfolioCaseItem(
    val caseImage: String,
    val caseName: String,
    val caseAmount: Int,
    val casePrice: Double,
    val caseOverallValue: Double,
    val caseProfitLoss: Double
): Parcelable
