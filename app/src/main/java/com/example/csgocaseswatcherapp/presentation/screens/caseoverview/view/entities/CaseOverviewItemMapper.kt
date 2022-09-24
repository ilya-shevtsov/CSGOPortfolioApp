package com.example.csgocaseswatcherapp.presentation.screens.caseoverview.view.entities

import com.example.csgocaseswatcherapp.presentation.screens.caseoverview.domain.entities.CaseOverview

object CaseOverviewItemMapper {

    fun map(caseOverview: CaseOverview): CaseOverviewModel {
        return CaseOverviewModel(
            caseName = caseOverview.name,
            lowestPrice = caseOverview.lowestPrice,
            volume = caseOverview.volume,
            medianPrice = caseOverview.medianPrice,
            imageUrl = caseOverview.imageUrl,
            releaseDate = caseOverview.releaseDate,
            dropStatus = caseOverview.dropStatus,
            description = caseOverview.description
        )
    }
}