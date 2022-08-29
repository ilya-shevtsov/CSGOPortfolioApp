package com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem

import android.util.Log
import com.example.csgocaseswatcherapp.presentation.model.addcaseitem.AddedCaseModel
import kotlin.math.round

object PortfolioItemMapper {

    //PlaceHolder caseProfitLoss and overallValue (right now is just from purchase price)

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

    fun getOverAllValue(amount: Int, price: Double): Double {
        Log.e("getOverAllValue","This is the amount: $amount")
        Log.e("getOverAllValue","This is the price: $price")
        return round(amount * price)
    }


    fun getCaseImage(caseName: String): String {
        val newName = caseName
            .replace(" ", "%20")
            .replace(":", "%3A")
            .replace("&", "%26")
        Log.e("addCase","This is newName $newName")
        return "https://api.steamapis.com/image/item/730/$newName"
    }
}
