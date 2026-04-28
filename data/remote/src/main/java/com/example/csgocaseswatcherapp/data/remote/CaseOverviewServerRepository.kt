package com.example.csgocaseswatcherapp.data.remote

import com.example.csgocaseswatcherapp.api.ServerApi
import com.example.csgocaseswatcherapp.features.caseoverview.data.CaseOverviewMapper
import com.example.csgocaseswatcherapp.features.caseoverview.domain.CaseRepository
import com.example.csgocaseswatcherapp.features.caseoverview.domain.entities.CaseOverview
import javax.inject.Inject

class CaseOverviewServerRepository @Inject constructor(
    private val api: ServerApi
) : CaseRepository {

    override suspend fun getCaseOverviewList(): List<CaseOverview> {
        val caseDtoListResponse = api.getCaseList()
        val caseOverViewList =
            caseDtoListResponse.map { caseDto -> CaseOverviewMapper.map(caseDto) }
        return caseOverViewList
    }
}