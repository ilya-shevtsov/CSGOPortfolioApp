package com.example.csgocaseswatcherapp.presentation.view.fragments.caseoverview.domain

import com.example.csgocaseswatcherapp.presentation.view.fragments.caseoverview.domain.entities.CaseOverview

interface CaseRepository {
    suspend fun getCaseOverviewList(): List<CaseOverview>
}