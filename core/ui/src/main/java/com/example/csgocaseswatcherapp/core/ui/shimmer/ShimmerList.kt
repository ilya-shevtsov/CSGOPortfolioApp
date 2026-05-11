package com.example.csgocaseswatcherapp.core.ui.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme

@Composable
fun ShimmerList(
    modifier: Modifier = Modifier,
    itemCount: Int = 6,
    itemContent: @Composable () -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colors.background),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.paddingML)
    ) {
        items(itemCount) {
            itemContent()
        }
    }
}