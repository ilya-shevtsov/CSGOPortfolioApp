package com.example.csgocaseswatcherapp.features.addcasefragment.view.entities

import java.io.Serializable

data class AddedCase(
    val name: String,
    val amount: Int,
    val purchasePrice: Double
) : Serializable


fun AddedCase.toModel(): AddCaseModel {
    return AddCaseModel(name = name, amount = amount.toString(), price = purchasePrice.toString())
}