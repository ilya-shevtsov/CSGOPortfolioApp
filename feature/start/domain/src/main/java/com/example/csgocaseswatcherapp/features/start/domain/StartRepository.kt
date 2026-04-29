package com.example.csgocaseswatcherapp.features.start.domain

import com.example.csgocaseswatcherapp.features.start.domain.entities.PreferredCurrency

interface StartRepository {

    suspend fun getPreferredCurrency(): PreferredCurrency

    fun sendPreferredCurrency(preferredCurrency: PreferredCurrency)
}
