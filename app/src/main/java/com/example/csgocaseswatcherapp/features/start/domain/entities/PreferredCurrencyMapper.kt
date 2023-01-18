package com.example.csgocaseswatcherapp.features.start.domain.entities

import com.example.csgocaseswatcherapp.features.start.data.entities.PreferredCurrencyDto

object PreferredCurrencyMapper {

    fun map(preferredCurrencyDto: PreferredCurrencyDto): PreferredCurrency {
        return PreferredCurrency(
            preferredCurrency = preferredCurrencyDto.preferredCurrency
        )
    }
}