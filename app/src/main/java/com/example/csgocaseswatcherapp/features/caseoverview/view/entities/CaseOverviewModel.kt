package com.example.csgocaseswatcherapp.features.caseoverview.view.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CaseOverviewModel(
    val caseName: String,
    val lowestPrice: Double,
    val volume: Int,
    val medianPrice: Double,
    val imageUrl: String,
    val releaseDate: String,
    val dropStatus: String,
    val description: String
) : Parcelable
