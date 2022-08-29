package com.example.csgocaseswatcherapp.presentation.model.addcaseitem

import java.io.Serializable

data class AddedCaseModel(
    val name: String,
    val amount: Int,
    val purchasePrice: Double
):Serializable