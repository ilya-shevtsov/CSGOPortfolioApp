package com.example.csgocaseswatcherapp.features.currencychange.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.example.csgocaseswatcherapp.core.ui.LoadingScreen
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme

@Composable
fun CurrencyChangeScreen(
    state: CurrencyChangeViewState,
    onCurrencyClicked: (String) -> Unit
) {

    when (state) {
        is CurrencyChangeViewState.Loading -> LoadingScreen()
        is CurrencyChangeViewState.Content -> {
            val items = state.currencyList
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppTheme.colors.background),
                contentPadding = PaddingValues(
                    horizontal = AppTheme.dimensions.paddingXS,
                    vertical = AppTheme.dimensions.paddingM
                ),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.paddingM)
            ) {
                items(items) { item ->
                    CurrencyChangeItem(
                        item = item,
                        onClick = { onCurrencyClicked(item) }
                    )
                }
            }
        }
    }
}



@PreviewLightDark
@Composable
fun CurrencyChangeScreenPreview() {
    AppTheme {
        CurrencyChangeScreen(
            state = CurrencyChangeViewState.Content(listOf("RUB", "USD")),
            onCurrencyClicked = {})
    }
}