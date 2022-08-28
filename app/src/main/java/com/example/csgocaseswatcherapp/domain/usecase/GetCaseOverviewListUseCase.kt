package com.example.csgocaseswatcherapp.domain.usecase

import com.example.csgocaseswatcherapp.domain.model.caseoverview.CaseOverview
import com.example.csgocaseswatcherapp.domain.repository.CaseRepository
import io.reactivex.Single
import javax.inject.Inject

class GetCaseOverviewListUseCase @Inject constructor(
    private val caseRepository: CaseRepository
) {

    suspend fun getCaseOverviewList(): List<CaseOverview> {
        return caseRepository.getCaseOverviewList()
    }
}