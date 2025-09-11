package com.example.csgocaseswatcherapp.features.portfolio.view.entities

import android.util.Log
import com.example.csgocaseswatcherapp.features.addcasefragment.view.entities.AddedCase
import kotlin.math.round

object PortfolioItemMapper {

    fun map(addedCaseItem: AddedCase): PortfolioItem {
        return PortfolioItem(
            image = getCaseImage(addedCaseItem.name),
            name = addedCaseItem.name,
            amount = addedCaseItem.amount,
            price = addedCaseItem.purchasePrice,
            overallValue = getOverAllValue(
                addedCaseItem.amount,
                addedCaseItem.purchasePrice
            ),
            profitLoss = 500.0
        )
    }

    private fun getOverAllValue(amount: Int, price: Double): Double {
        return round(amount * price)
    }

    private fun getCaseImage(caseName: String): String {
        val newName = caseName
            .replace(" ", "%20")
            .replace(":", "%3A")
            .replace("&", "%26")
        Log.e("addCase","This is newName $newName")
        return "https://api.steamapis.com/image/item/730/$newName"
    }
}

