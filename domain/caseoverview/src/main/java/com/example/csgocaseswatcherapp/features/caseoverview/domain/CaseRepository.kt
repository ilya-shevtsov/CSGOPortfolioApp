package com.example.csgocaseswatcherapp.features.caseoverview.domain

import com.example.csgocaseswatcherapp.features.caseoverview.domain.entities.CaseOverview


interface CaseRepository {
    suspend fun getCaseOverviewList(): List<CaseOverview>
}