package com.example.csgocaseswatcherapp.core.ui.preview

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.core.ui.topbar.AppTopBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreviewWithTopBar(
    title: String,
    canNavigateBack: Boolean = false,
    isCompact: Boolean = false,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable (PaddingValues) -> Unit
) {
    AppTheme(darkTheme = darkTheme) {
        Scaffold(
            topBar = {
                AppTopBar(
                    title = title,
                    canNavigateBack = canNavigateBack,
                    onBack = {},
                    isCompact = isCompact,
                    scrollBehavior = null
                )
            },
            contentWindowInsets = WindowInsets.safeContent,
            containerColor = AppTheme.colors.background
        ) { paddingValues ->
            content(paddingValues)
        }
    }
}