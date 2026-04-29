package com.example.csgocaseswatcherapp.features.start.data.entities

import com.example.csgocaseswatcherapp.features.start.domain.entities.PreferredCurrency

object PreferredCurrencyDtoMapper {

    fun map(preferredCurrency: PreferredCurrency): PreferredCurrencyDto{
        return PreferredCurrencyDto(
            preferredCurrency = preferredCurrency.preferredCurrency
        )
    }
}