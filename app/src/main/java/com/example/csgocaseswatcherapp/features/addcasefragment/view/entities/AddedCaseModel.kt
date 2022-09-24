package com.example.csgocaseswatcherapp.features.addcasefragment.view.entities

import java.io.Serializable

data class AddedCaseModel(
    val name: String,
    val amount: Int,
    val purchasePrice: Double
):Serializable