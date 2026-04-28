package com.example.csgocaseswatcherapp.data.local

import android.util.Log
import com.example.csgocaseswatcherapp.features.start.data.PreferredCurrencyMapper
import com.example.csgocaseswatcherapp.features.start.data.entities.PreferredCurrencyDto
import com.example.csgocaseswatcherapp.features.start.domain.StartRepository
import com.example.csgocaseswatcherapp.features.start.domain.entities.PreferredCurrency
import javax.inject.Inject

class LocalStartServerRepository @Inject constructor() : StartRepository {

    private var mockServerResponse = PreferredCurrencyDto(1)

    override suspend fun getPreferredCurrency(): PreferredCurrency {
        val response = mockServerResponse
        return PreferredCurrencyMapper.map(response)
    }

    override fun sendPreferredCurrency(preferredCurrency: PreferredCurrency) {
        Log.e("KEK","sendPreferredCurrency: $preferredCurrency")
        mockServerResponse = PreferredCurrencyDto(preferredCurrency.preferredCurrency)
    }
}