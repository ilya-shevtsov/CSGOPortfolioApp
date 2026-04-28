package com.example.csgocaseswatcherapp.data.local

import com.example.csgocaseswatcherapp.features.addcase.domain.entities.AddedCase
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
        _portfolio.update { currentPortfolio ->
            val addedCaseAlreadyInPortfolio = currentPortfolio.any { it.name == added.name }
            if (addedCaseAlreadyInPortfolio) {
                currentPortfolio.map { case ->
                    if (case.name == added.name) mergeCase(
                        case,
                        added,
                        imageUrl
                    ) else case
                }
            } else {
                currentPortfolio + newCase(added, imageUrl)
            }
        }
    }

    private fun mergeCase(
        case: PortfolioItemDto,
        added: AddedCase,
        fallbackImageUrl: String
    ): PortfolioItemDto {
        val newAmount = case.amount + added.amount
        val newPrice = getNewPurchasePrice(case, added, newAmount)
        return case.copy(
            amount = newAmount,
            purchasePrice = newPrice,
            overallValue = newPrice * newAmount,
            imageUrl = case.imageUrl.ifBlank { fallbackImageUrl }
        )
    }

    private fun newCase(added: AddedCase, imageUrl: String) = PortfolioItemDto(
        name = added.name,
        amount = added.amount,
        purchasePrice = added.purchasePrice,
        overallValue = added.purchasePrice * added.amount,
        profitLoss = 0.0,
        imageUrl = imageUrl
    )

    private fun getNewPurchasePrice(
        existingCaseData: PortfolioItemDto,
        addedCase: AddedCase,
        newAmount: Int
    ) =
        ((existingCaseData.purchasePrice * existingCaseData.amount) + (addedCase.purchasePrice * addedCase.amount)) / newAmount
}