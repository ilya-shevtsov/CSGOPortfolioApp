package com.example.csgocaseswatcherapp.core.navigation

import com.example.csgocaseswatcherapp.core.serialization.NavArgCodec.listSerializer
import com.example.csgocaseswatcherapp.core.serialization.navTypeOf
import com.example.csgocaseswatcherapp.features.caseoverview.view.entities.CaseOverviewModel
import com.example.csgocaseswatcherapp.features.portfolio.domain.entities.PortfolioItem


object CustomNavType {
    val CaseOverviewModelType = navTypeOf(CaseOverviewModel.serializer())

    val PortfolioItemListType = navTypeOf(listSerializer(PortfolioItem.serializer()))
}