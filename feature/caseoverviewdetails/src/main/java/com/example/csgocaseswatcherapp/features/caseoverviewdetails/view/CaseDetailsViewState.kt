package com.example.csgocaseswatcherapp.features.caseoverviewdetails.view

import com.example.csgocaseswatcherapp.features.caseoverviewdetails.view.entities.DataRowModel

sealed class CaseDetailsViewState {

    data object Loading : CaseDetailsViewState()

    data class Content(
        val caseName: String,
        val imageUrl: String,
        val description: String,
        val dataRowModelList: List<DataRowModel>
    ) : CaseDetailsViewState()
}


