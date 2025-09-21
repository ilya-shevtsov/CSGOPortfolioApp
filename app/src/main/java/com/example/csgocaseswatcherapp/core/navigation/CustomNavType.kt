package com.example.csgocaseswatcherapp.core.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.example.csgocaseswatcherapp.features.caseoverview.view.entities.CaseOverviewModel
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItem
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {
    val CaseOverviewModelType = object : NavType<CaseOverviewModel>(isNullableAllowed = false) {

        override fun get(bundle: Bundle, key: String): CaseOverviewModel? {
            val encoded = bundle.getString(key) ?: return null
            return Json.decodeFromString(Uri.decode(encoded))
        }

        override fun parseValue(value: String): CaseOverviewModel {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: CaseOverviewModel): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: CaseOverviewModel) {
            bundle.putString(key, serializeAsValue(value))
        }
    }

    val PortfolioItemListType = object : NavType<List<PortfolioItem>>(isNullableAllowed = false) {

        override fun get(bundle: Bundle, key: String): List<PortfolioItem>? {
            val encoded = bundle.getString(key) ?: return null
            return Json.decodeFromString(Uri.decode(encoded))
        }

        override fun parseValue(value: String): List<PortfolioItem> {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: List<PortfolioItem>): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: List<PortfolioItem>) {
            bundle.putString(key, serializeAsValue(value))
        }
    }
}