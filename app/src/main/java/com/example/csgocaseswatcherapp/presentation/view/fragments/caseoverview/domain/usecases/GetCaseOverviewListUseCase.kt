package com.example.csgocaseswatcherapp.presentation.view.fragments.caseoverview.domain.usecases

import com.example.csgocaseswatcherapp.presentation.view.fragments.caseoverview.domain.CaseRepository
import com.example.csgocaseswatcherapp.presentation.view.fragments.caseoverview.domain.entities.CaseOverview
import javax.inject.Inject

class GetCaseOverviewListUseCase @Inject constructor(
    private val caseRepository: CaseRepository
) {

    suspend fun getCaseOverviewList(): List<CaseOverview> {
        return caseRepository.getCaseOverviewList()
    }
}