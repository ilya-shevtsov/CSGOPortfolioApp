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
                amount = 200,
                purchasePrice = 2.30,
                overallValue = 460.00,
                profitLoss = 60.00,
                imageUrl = "https://api.steamapis.com/image/item/730/Chroma%203%20Case"
            ),
            PortfolioItemDto(
                name = "eSports 2013 Case",
                amount = 1,
                purchasePrice = 72.50,
                overallValue = 72.50,
                profitLoss = 66.50,
                imageUrl = "https://api.steamapis.com/image/item/730/eSports%202013%20Case"
            ),
            PortfolioItemDto(
                name = "Revolution Case",
                amount = 75,
                purchasePrice = 3.00,
                overallValue = 225.00,
                profitLoss = -37.50,
                imageUrl = "https://api.steamapis.com/image/item/730/Revolution%20Case"
            ),
            PortfolioItemDto(
                name = "Chroma Case",
                amount = 32,
                purchasePrice = 3.00,
                overallValue = 96.00,
                profitLoss = 0.00,
                imageUrl = "https://api.steamapis.com/image/item/730/Chroma%20Case"
            ),
            PortfolioItemDto(
                name = "Dreams & Nightmares Case",
                amount = 150,
                purchasePrice = 2.35,
                overallValue = 352.50,
                profitLoss = 52.50,
                imageUrl = "https://api.steamapis.com/image/item/730/Dreams%20%26%20Nightmares%20Case"
            ),
            PortfolioItemDto(
                name = "Snakebite Case",
                amount = 300,
                purchasePrice = 0.28,
                overallValue = 84.00,
                profitLoss = -21.00,
                imageUrl = "https://api.steamapis.com/image/item/730/Snakebite%20Case"
            ),
            PortfolioItemDto(
                name = "Operation Bravo Case",
                amount = 3,
                purchasePrice = 133.55,
                overallValue = 400.65,
                profitLoss = 340.65,
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