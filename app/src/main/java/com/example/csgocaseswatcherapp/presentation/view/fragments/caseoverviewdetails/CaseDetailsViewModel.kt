package com.example.csgocaseswatcherapp.presentation.view.fragments.caseoverviewdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.csgocaseswatcherapp.presentation.model.caseoverviewitem.CaseOverviewModel
import javax.inject.Inject

class CaseDetailsViewModel @Inject constructor(
) : ViewModel() {

    val viewStateLiveData = MutableLiveData<CaseDetailsViewState>()

    fun onItemProvided(currentCase: CaseOverviewModel) {
        with(currentCase) {
            val state = CaseDetailsViewState(
                caseName = caseName,
                lowestPrice = lowestPrice.toString(),
                volume = volume.toString(),
                medianPrice = medianPrice.toString(),
                caseImage = imageUrl,
                releaseDate = releaseDate,
                dropStatus = dropStatus,
                description = description
            )
            viewStateLiveData.postValue(state)
        }
    }
}
