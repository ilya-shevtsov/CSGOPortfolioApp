package com.example.csgocaseswatcherapp.features.portfolio.view.sorting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.portfolio.domain.model.PortfolioSortType

@Composable
fun SortingScreen(
    state: SortingModalViewState,
    onClick: (method: PortfolioSortType) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .background(AppTheme.colors.background),
        contentPadding = PaddingValues(AppTheme.dimensions.paddingM),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.paddingM)
    ) {
        item {
            Text(
                text = "Sort",
                style = MaterialTheme.typography.titleLarge,
                color = AppTheme.colors.primary
            )
        }

        items(
            items = state.sortingEntryList,
            key = { entry -> entry.sortType }
        ) { entry ->
            Button(
                onClick = { onClick(entry.sortType) },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 44.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppTheme.colors.primary,
                    contentColor = AppTheme.colors.onPrimary,
                ),
                shape = AppTheme.shapes.buttonNormal,
                contentPadding = PaddingValues(
                    horizontal = AppTheme.dimensions.paddingM,
                    vertical = AppTheme.dimensions.paddingS
                )
            ) {
                Text(
                    text = stringResource(entry.resId),
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun SortingScreenPreview() {
    AppTheme {
        SortingScreen(
            state = SortingModalViewState(listOf()),
            onClick = {}
        )
    }
}
