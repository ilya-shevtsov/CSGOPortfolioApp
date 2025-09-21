package com.example.csgocaseswatcherapp.features.caseoverviewdetails.view

import androidx.lifecycle.ViewModel
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.features.caseoverview.view.entities.CaseOverviewModel
import com.example.csgocaseswatcherapp.features.caseoverviewdetails.view.entities.DataRowModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
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
            imageUrl = model.imageUrl,
            description = model.description,
            dataRowModelList = listOf(
                DataRowModel(
                    labelId = R.string.case_lowest_price,
                    value = NumberFormat.getCurrencyInstance(Locale.US).format(model.lowestPrice)
                ),
                DataRowModel(
                    labelId = R.string.case_volume,
                    value = NumberFormat.getIntegerInstance(Locale.US).format(model.volume)
                ),
                DataRowModel(
                    labelId = R.string.case_median_price,
                    value = NumberFormat.getCurrencyInstance(Locale.US).format(model.medianPrice)
                ),
                DataRowModel(
                    labelId = R.string.case_release_date,
                    value = model.releaseDate
                ),
                DataRowModel(
                    labelId = R.string.case_drop_status,
                    value = model.dropStatus
                )
            )
        )
        uiState.value = state
    }
}
