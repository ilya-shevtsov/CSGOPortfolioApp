package com.example.csgocaseswatcherapp.presentation.view.fragments.start

sealed class StartViewAction {

    data class OnCurrencySelected(
        val preferredCurrency: String?
    ) : StartViewAction()
    
    
}