package com.example.csgocaseswatcherapp.core.ui.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    title: String,
    canNavigateBack: Boolean,
    onBack: () -> Unit,
    isCompact: Boolean,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    if (isCompact) {
        CompactAppTopBar(
            title = title,
            canNavigateBack = canNavigateBack,
            onBack = onBack,
            modifier = modifier
        )
    } else {
        RegularAppTopBar(
            title = title,
            canNavigateBack = canNavigateBack,
            onBack = onBack,
            scrollBehavior = scrollBehavior,
            modifier = modifier
        )
    }
}

@Composable
private fun CompactAppTopBar(
    title: String,
    canNavigateBack: Boolean,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(AppTheme.colors.background)
            .windowInsetsPadding(WindowInsets.safeContent)
            .padding(start = AppTheme.dimensions.paddingM)
            .height(AppTheme.dimensions.compactTopBarHeight),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (canNavigateBack) {
            IconButton(
                onClick = onBack,
                modifier = Modifier.size(AppTheme.dimensions.backIconSize)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = AppTheme.colors.onBackground
                )
            }
        }

        Text(
            text = title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = AppTheme.colors.onBackground,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .weight(1f)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RegularAppTopBar(
    title: String,
    canNavigateBack: Boolean,
    onBack: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior?,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge,
                color = AppTheme.colors.onBackground
            )
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = AppTheme.colors.onBackground
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = AppTheme.colors.background,
            scrolledContainerColor = AppTheme.colors.background,
            titleContentColor = AppTheme.colors.onBackground,
            navigationIconContentColor = AppTheme.colors.onBackground
        )
    )
}