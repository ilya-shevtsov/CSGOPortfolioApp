package com.example.csgocaseswatcherapp.features.caseoverviewdetails.view

import androidx.lifecycle.ViewModel
import com.example.csgocaseswatcherapp.features.caseoverview.view.entities.CaseOverviewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

class CaseDetailsViewModel @Inject constructor(
) : ViewModel() {

    val uiState: MutableStateFlow<CaseDetailsViewState> =
        MutableStateFlow(value = CaseDetailsViewState.Loading)

    fun handleAction(action: CaseDetailsViewAction) {
        when (action) {
            is CaseDetailsViewAction.OnItemProvided -> handleOnItemProvided(action.caseOverviewModel)

        }
    }

    private fun handleOnItemProvided(model: CaseOverviewModel) {
        val state = CaseDetailsViewState.Content(
            caseName = model.caseName,
            lowestPrice = NumberFormat.getCurrencyInstance(Locale.US).format(model.lowestPrice),
            volume = NumberFormat.getIntegerInstance(Locale.US).format(model.volume),
            medianPrice = NumberFormat.getCurrencyInstance(Locale.US).format(model.medianPrice),
            imageUrl = model.imageUrl,
            releaseDate = model.releaseDate,
            dropStatus = model.dropStatus,
            description = model.description
        )
        uiState.value = state
    }
}
