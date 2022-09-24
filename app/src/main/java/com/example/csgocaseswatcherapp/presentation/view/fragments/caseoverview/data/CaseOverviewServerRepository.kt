package com.example.csgocaseswatcherapp.presentation.view.fragments.caseoverview.data

import com.example.csgocaseswatcherapp.data.api.ApiTools
import com.example.csgocaseswatcherapp.presentation.view.fragments.caseoverview.data.entities.CaseOverviewMapper
import com.example.csgocaseswatcherapp.presentation.view.fragments.caseoverview.domain.entities.CaseOverview
import com.example.csgocaseswatcherapp.presentation.view.fragments.caseoverview.domain.CaseRepository
import javax.inject.Inject

class CaseOverviewServerRepository @Inject constructor(

) : CaseRepository {

    override suspend fun getCaseOverviewList(): List<CaseOverview> {
        val caseDtoListResponse = ApiTools.getApiService().getCaseList()
        return caseDtoListResponse.map { caseDto -> CaseOverviewMapper.map(caseDto) }
    }
}


