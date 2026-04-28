package com.example.csgocaseswatcherapp.features.caseoverview.view

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.example.csgocaseswatcherapp.core.ui.ErrorScreen
import com.example.csgocaseswatcherapp.core.ui.LoadingScreen
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.caseoverview.view.entities.CaseOverviewItem
import com.example.csgocaseswatcherapp.features.caseoverview.view.entities.CaseOverviewModel

@Composable
fun CaseOverviewScreen(
    state: CaseOverviewViewState,
    onCaseClick: (CaseOverviewModel) -> Unit,
) {

    when (state) {
        is CaseOverviewViewState.Error -> ErrorScreen()

        is CaseOverviewViewState.Loading -> LoadingScreen()

        is CaseOverviewViewState.Content -> {
            val items = state.caseOverviewItemList
            LazyColumn(modifier = Modifier.background(AppTheme.colors.background)) {
                items(
                    items = items,
                ) { item ->
                    CaseOverviewItem(
                        item = item,
                        onClick = { onCaseClick(item) }
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun CaseOverviewScreenPreview() {
    AppTheme{
        val item = CaseOverviewModel(
            caseName = "Chroma Case",
            lowestPrice = 6.63,
            volume = 1013,
            medianPrice = 7.45,
            imageUrl = "https://api.steamapis.com/image/item/730/Recoil%20Case",
            releaseDate = "01.07.2022",
            dropStatus = "Active",
            description = "—"
        )
        CaseOverviewScreen(
            state = CaseOverviewViewState.Content(
                caseOverviewItemList = listOf(
                    item,
                    item,
                    item,
                    item,
                    item,
                )
            ),
            onCaseClick = {}
        )
    }
}
