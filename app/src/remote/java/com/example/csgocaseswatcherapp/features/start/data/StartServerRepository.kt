package com.example.csgocaseswatcherapp.features.start.data

import com.example.csgocaseswatcherapp.api.ApiTools
import com.example.csgocaseswatcherapp.features.start.data.entities.PreferredCurrencyDtoMapper
import com.example.csgocaseswatcherapp.features.start.domain.StartRepository
import com.example.csgocaseswatcherapp.features.start.domain.entities.PreferredCurrency
import com.example.csgocaseswatcherapp.features.start.domain.entities.PreferredCurrencyMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class StartServerRepository @Inject constructor() : StartRepository {

    override suspend fun getPreferredCurrency(): PreferredCurrency {
        val response = ApiTools.getApiService().getPreferredCurrency()
        return PreferredCurrencyMapper.map(response)
    }

    override fun sendPreferredCurrency(preferredCurrency: PreferredCurrency) {
        CoroutineScope(Dispatchers.IO).launch {
            val mappedPreferredCurrency = PreferredCurrencyDtoMapper.map(preferredCurrency)
            ApiTools.getApiService().postPreferredCurrency(mappedPreferredCurrency)
        }
    }


}
