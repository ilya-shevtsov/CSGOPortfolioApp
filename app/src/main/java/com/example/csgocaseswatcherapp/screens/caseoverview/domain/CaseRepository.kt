package com.example.csgocaseswatcherapp.screens.caseoverview.domain

import com.example.csgocaseswatcherapp.screens.caseoverview.domain.entities.CaseOverview


interface CaseRepository {
    suspend fun getCaseOverviewList(): List<CaseOverview>
}