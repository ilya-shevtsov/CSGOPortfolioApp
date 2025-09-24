package com

import com.example.csgocaseswatcherapp.features.addcasefragment.view.entities.AddedCase
import com.example.csgocaseswatcherapp.features.portfolio.data.entities.PortfolioItemDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalMockStore @Inject constructor() {

    private val _portfolio = MutableStateFlow(
        listOf(
            PortfolioItemDto(
                name = "Chroma 3 Case",
                amount = 20,
                purchasePrice = 2.0,
                overallValue = 40.0,
                profitLoss = 0.0,
                imageUrl = "https://api.steamapis.com/image/item/730/Chroma%203%20Case"
            ),
            PortfolioItemDto(
                name = "eSports 2013 Case",
                amount = 1,
                purchasePrice = 6.0,
                overallValue = 6.0,
                profitLoss = 0.0,
                imageUrl = "https://api.steamapis.com/image/item/730/eSports%202013%20Case"
            ),
            PortfolioItemDto(
                name = "Chroma Case",
                amount = 2,
                purchasePrice = 3.0,
                overallValue = 6.0,
                profitLoss = 0.0,
                imageUrl = "https://api.steamapis.com/image/item/730/Chroma%20Case"
            ),
            PortfolioItemDto(
                name = "Operation Bravo Case",
                amount = 3,
                purchasePrice = 2.0,
                overallValue = 6.0,
                profitLoss = 0.0,
                imageUrl = "https://api.steamapis.com/image/item/730/Operation%20Bravo%20Case"
            )
        )
    )
    val portfolio: StateFlow<List<PortfolioItemDto>> = _portfolio

    fun addCaseToPortfolio(added: AddedCase, imageUrl: String) {
        _portfolio.update { currentState ->
            val caseIndex = currentState.indexOfFirst { it.name == added.name }
            if (caseIndex >= 0) {
                val caseData = currentState[caseIndex]
                val newAmount = caseData.amount + added.amount
                val newPurchasePrice =
                    if (newAmount == 0) caseData.purchasePrice
                    else ((caseData.purchasePrice * caseData.amount) + (added.purchasePrice * added.amount)) / newAmount

                val updated = caseData.copy(
                    amount = newAmount,
                    purchasePrice = newPurchasePrice,
                    overallValue = newPurchasePrice * newAmount,
                    imageUrl = caseData.imageUrl.ifBlank { imageUrl }
                )
                currentState.toMutableList().apply { set(caseIndex, updated) }
            } else {
                currentState + PortfolioItemDto(
                    name = added.name,
                    amount = added.amount,
                    purchasePrice = added.purchasePrice,
                    overallValue = added.purchasePrice * added.amount,
                    profitLoss = 0.0,
                    imageUrl = imageUrl
                )
            }
        }
    }
}