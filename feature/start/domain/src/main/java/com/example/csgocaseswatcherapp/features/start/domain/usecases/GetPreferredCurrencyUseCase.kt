package com.example.csgocaseswatcherapp.features.start.domain.usecases

import com.example.csgocaseswatcherapp.features.start.domain.StartRepository
import com.example.csgocaseswatcherapp.features.start.domain.entities.PreferredCurrency
import javax.inject.Inject

class GetPreferredCurrencyUseCase @Inject constructor(
    private val startRepository: StartRepository
) {
    suspend operator fun invoke(): PreferredCurrency {
        return startRepository.getPreferredCurrency()
    }
}
