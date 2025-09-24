package com.example.csgocaseswatcherapp.features.caseoverview.data

import com.example.csgocaseswatcherapp.api.ApiTools
import com.example.csgocaseswatcherapp.features.caseoverview.data.entities.CaseOverviewMapper
import com.example.csgocaseswatcherapp.features.caseoverview.domain.CaseRepository
import com.example.csgocaseswatcherapp.features.caseoverview.domain.entities.CaseOverview

import javax.inject.Inject

class CaseOverviewServerRepository @Inject constructor(

) : CaseRepository {

    override suspend fun getCaseOverviewList(): List<CaseOverview> {
        val caseDtoListResponse = ApiTools.getApiService().getCaseList()
        val caseOverViewList =
            caseDtoListResponse.map { caseDto -> CaseOverviewMapper.map(caseDto) }
        return caseOverViewList
    }
}



