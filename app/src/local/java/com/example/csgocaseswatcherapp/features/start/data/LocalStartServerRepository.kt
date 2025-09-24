package com.example.csgocaseswatcherapp.features.start.data

import com.example.csgocaseswatcherapp.features.start.data.entities.PreferredCurrencyDto
import com.example.csgocaseswatcherapp.features.start.domain.StartRepository
import com.example.csgocaseswatcherapp.features.start.domain.entities.PreferredCurrency
import com.example.csgocaseswatcherapp.features.start.domain.entities.PreferredCurrencyMapper
import javax.inject.Inject

class LocalStartServerRepository @Inject constructor() : StartRepository {

    private val mockServerResponse = PreferredCurrencyDto(1)

    override suspend fun getPreferredCurrency(): PreferredCurrency {
        val response = mockServerResponse
        return PreferredCurrencyMapper.map(response)
    }

    override fun sendPreferredCurrency(preferredCurrency: PreferredCurrency) {
        // TO-DO
    }
}
