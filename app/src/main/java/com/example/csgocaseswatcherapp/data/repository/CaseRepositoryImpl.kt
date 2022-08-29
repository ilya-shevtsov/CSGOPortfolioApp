package com.example.csgocaseswatcherapp.data.repository

import com.example.csgocaseswatcherapp.data.api.ApiTools
import com.example.csgocaseswatcherapp.data.model.caseoverview.CaseOverviewMapper
import com.example.csgocaseswatcherapp.domain.model.caseoverview.CaseOverview
import com.example.csgocaseswatcherapp.domain.repository.CaseRepository
import javax.inject.Inject

class CaseRepositoryImpl @Inject constructor(

) : CaseRepository {

    override suspend fun getCaseOverviewList(): List<CaseOverview> {
        val caseDtoListResponse = ApiTools.getApiService().getCaseList()
        return caseDtoListResponse.map { caseDto -> CaseOverviewMapper.map(caseDto) }
    }
}


