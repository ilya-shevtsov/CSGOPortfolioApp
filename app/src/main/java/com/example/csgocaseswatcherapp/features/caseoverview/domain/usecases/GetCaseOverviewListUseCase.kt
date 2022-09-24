package com.example.csgocaseswatcherapp.features.caseoverview.domain.usecases

import com.example.csgocaseswatcherapp.features.caseoverview.domain.CaseRepository
import com.example.csgocaseswatcherapp.features.caseoverview.domain.entities.CaseOverview
import javax.inject.Inject

class GetCaseOverviewListUseCase @Inject constructor(
    private val caseRepository: CaseRepository
) {

    suspend fun getCaseOverviewList(): List<CaseOverview> {
        return caseRepository.getCaseOverviewList()
    }
}