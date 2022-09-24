package com.example.csgocaseswatcherapp.presentation.view.fragments.caseoverview.data.entities

import com.example.csgocaseswatcherapp.presentation.view.fragments.caseoverview.domain.entities.CaseOverview

object CaseOverviewMapper {

    fun map(
        caseDto: CaseDto,
    ): CaseOverview {
        return CaseOverview(
            name = caseDto.name,
            lowestPrice = caseDto.lowestPrice,
            volume = caseDto.volume,
            medianPrice = caseDto.medianPrice,
            imageUrl = caseDto.imageUrl,
            dropStatus = caseDto.dropStatus,
            releaseDate = caseDto.releaseDate,
            description = caseDto.description
        )
    }
}