package com.example.csgocaseswatcherapp.features.start.data

import com.example.csgocaseswatcherapp.features.start.data.entities.PreferredCurrencyDto
import com.example.csgocaseswatcherapp.features.start.domain.entities.PreferredCurrency

object PreferredCurrencyMapper {

    fun map(preferredCurrencyDto: PreferredCurrencyDto): PreferredCurrency {
        return PreferredCurrency(
            preferredCurrency = preferredCurrencyDto.preferredCurrency
        )
    }
}