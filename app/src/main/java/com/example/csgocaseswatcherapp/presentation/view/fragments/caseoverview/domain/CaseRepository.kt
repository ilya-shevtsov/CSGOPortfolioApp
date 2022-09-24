package com.example.csgocaseswatcherapp.presentation.view.fragments.caseoverview.domain

import com.example.csgocaseswatcherapp.domain.model.caseoverview.CaseOverview
import io.reactivex.Single

interface CaseRepository {
    suspend fun getCaseOverviewList(): List<CaseOverview>
}