package com.example.csgocaseswatcherapp.presentation.view.fragments.caseoverview.domain

import com.example.csgocaseswatcherapp.domain.model.caseoverview.CaseOverview
import javax.inject.Inject

class GetCaseOverviewListUseCase @Inject constructor(
    private val caseRepository: CaseRepository
) {

    suspend fun getCaseOverviewList(): List<CaseOverview> {
        return caseRepository.getCaseOverviewList()
    }
}