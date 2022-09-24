package com.example.csgocaseswatcherapp.features.start.data

import com.example.csgocaseswatcherapp.api.ApiTools
import com.example.csgocaseswatcherapp.features.start.data.entities.PreferredCurrencyDto
import com.example.csgocaseswatcherapp.features.start.domain.StartRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class StartServerRepository @Inject constructor() : StartRepository {

    override suspend fun getPreferredCurrency(): PreferredCurrencyDto {
        return ApiTools.getApiService().getPreferredCurrency()

    }

    override fun sendPreferredCurrency(preferredCurrency: PreferredCurrencyDto) {
        CoroutineScope(Dispatchers.IO).launch {
            ApiTools.getApiService().postPreferredCurrency(preferredCurrency)
        }
    }


}