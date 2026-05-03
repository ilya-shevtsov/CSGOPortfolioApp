package com.example.csgocaseswatcherapp.core.ui.preview

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.core.ui.topbar.CompactLandscapeTopBar
import com.example.csgocaseswatcherapp.core.ui.topbar.MyAppTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreviewWithTopBar(
    title: String,
    canNavigateBack: Boolean = false,
    isLandscape: Boolean = false,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable (PaddingValues) -> Unit
) {
    AppTheme(darkTheme = darkTheme) {
        Scaffold(
            topBar = {
                if (isLandscape) {
                    CompactLandscapeTopBar(
                        title = title,
                        canNavigateBack = canNavigateBack,
                        onBack = {}
                    )
                } else {
                    MyAppTopBar(
                        title = title,
                        canNavigateBack = canNavigateBack,
                        onBack = {},
                        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(
                            state = rememberTopAppBarState()
                        )
                    )
                }
            },
            contentWindowInsets = WindowInsets(0.dp),
            containerColor = AppTheme.colors.background
        ) { paddingValues ->
            content(paddingValues)
        }
    }
}