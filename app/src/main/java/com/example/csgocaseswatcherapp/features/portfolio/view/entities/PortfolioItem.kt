package com.example.csgocaseswatcherapp.features.portfolio.view.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.Locale

@Parcelize
data class PortfolioItem(
    val caseImage: String,
    val caseName: String,
    val caseAmount: Int,
    val casePrice: Double,
    val caseOverallValue: Double,
    val caseProfitLoss: Double,
) : Parcelable


fun PortfolioItem.toModel():PortfolioItemModel {
    return PortfolioItemModel(
        itemImage = caseImage,
        itemName = caseName,
        totalValue = String.format(Locale.US, "$%.2f", caseOverallValue),
        amountPrice = "$caseAmount cases • ${
            String.format(Locale.US, "$%.2f", casePrice)
        }",
        profitLoss = "${if (caseProfitLoss >= 0) "+" else ""}${
            String.format(Locale.US, "%.2f", caseProfitLoss)
        } $ (${caseProfitLoss} %)"
    )
}



