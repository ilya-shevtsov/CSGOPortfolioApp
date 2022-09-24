package com.example.csgocaseswatcherapp.features.start.domain

import com.example.csgocaseswatcherapp.features.start.data.entities.PreferredCurrencyDto

interface StartRepository {

    suspend fun getPreferredCurrency(): PreferredCurrencyDto

    fun sendPreferredCurrency(preferredCurrency: PreferredCurrencyDto)
}