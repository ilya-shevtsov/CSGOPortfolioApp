package com.example.csgocaseswatcherapp.features.portfolio.view.entities

import android.util.Log
import com.example.csgocaseswatcherapp.features.addcasefragment.view.entities.AddedCaseModel
import kotlin.math.round

object PortfolioItemMapper {

    fun map(addedCaseItem: AddedCaseModel): PortfolioItem {
        return PortfolioItem(
            caseImage = getCaseImage(addedCaseItem.name),
            caseName = addedCaseItem.name,
            caseAmount = addedCaseItem.amount,
            casePrice = addedCaseItem.purchasePrice,
            caseOverallValue = getOverAllValue(
                addedCaseItem.amount,
                addedCaseItem.purchasePrice
            ),
            caseProfitLoss = 500.0
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

