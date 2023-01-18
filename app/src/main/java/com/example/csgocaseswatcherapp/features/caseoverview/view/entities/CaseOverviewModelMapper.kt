package com.example.csgocaseswatcherapp.features.caseoverview.view.entities

import com.example.csgocaseswatcherapp.features.caseoverview.domain.entities.CaseOverview

object CaseOverviewModelMapper {

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