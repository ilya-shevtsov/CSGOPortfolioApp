package com.example.csgocaseswatcherapp.presentation.screens.addcasefragment.view.entities

import java.io.Serializable

data class AddedCaseModel(
    val name: String,
    val amount: Int,
    val purchasePrice: Double
):Serializable