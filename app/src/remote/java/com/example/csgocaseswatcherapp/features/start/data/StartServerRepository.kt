package com.example.csgocaseswatcherapp.features.start.data

import com.example.csgocaseswatcherapp.api.ServerApi
import com.example.csgocaseswatcherapp.features.start.data.entities.PreferredCurrencyDtoMapper
import com.example.csgocaseswatcherapp.features.start.domain.StartRepository
import com.example.csgocaseswatcherapp.features.start.domain.entities.PreferredCurrency
import com.example.csgocaseswatcherapp.features.start.domain.entities.PreferredCurrencyMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class StartServerRepository @Inject constructor(
    private val api: ServerApi
) : StartRepository {

    override suspend fun getPreferredCurrency(): PreferredCurrency {
        val response = api.getPreferredCurrency()
        return PreferredCurrencyMapper.map(response)
    }

    override fun sendPreferredCurrency(preferredCurrency: PreferredCurrency) {
        CoroutineScope(Dispatchers.IO).launch {
            val mappedPreferredCurrency = PreferredCurrencyDtoMapper.map(preferredCurrency)
            api.postPreferredCurrency(mappedPreferredCurrency)
        }
    }
}
