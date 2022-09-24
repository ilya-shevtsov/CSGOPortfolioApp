package com.example.csgocaseswatcherapp.presentation.screens.caseoverview.domain

import com.example.csgocaseswatcherapp.presentation.screens.caseoverview.domain.entities.CaseOverview


interface CaseRepository {
    suspend fun getCaseOverviewList(): List<CaseOverview>
}