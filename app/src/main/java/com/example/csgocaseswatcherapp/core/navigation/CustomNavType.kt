package com.example.csgocaseswatcherapp.core.navigation

import com.example.csgocaseswatcherapp.core.serialization.navTypeOf
import com.example.csgocaseswatcherapp.features.caseoverview.view.entities.CaseOverviewModel
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItem
import kotlinx.serialization.builtins.ListSerializer


object CustomNavType {
    val CaseOverviewModelType = navTypeOf(CaseOverviewModel.serializer())

    val PortfolioItemListType = navTypeOf(ListSerializer(PortfolioItem.serializer()))
}