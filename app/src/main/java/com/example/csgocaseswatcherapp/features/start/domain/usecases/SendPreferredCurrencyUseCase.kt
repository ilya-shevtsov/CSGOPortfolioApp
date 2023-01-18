package com.example.csgocaseswatcherapp.features.start.domain.usecases

import com.example.csgocaseswatcherapp.features.start.data.entities.PreferredCurrencyDto
import com.example.csgocaseswatcherapp.features.start.domain.StartRepository
import com.example.csgocaseswatcherapp.features.start.domain.entities.PreferredCurrency
import javax.inject.Inject

class SendPreferredCurrencyUseCase @Inject constructor(
    private val startRepository: StartRepository
) {
    operator fun invoke(preferredCurrency: PreferredCurrency) {
        return startRepository.sendPreferredCurrency(preferredCurrency)
    }
}
