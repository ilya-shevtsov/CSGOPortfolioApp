package com.example.csgocaseswatcherapp.features.caseoverview.view.entities

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.csgocaseswatcherapp.core.ui.ErrorScreen
import com.example.csgocaseswatcherapp.core.ui.LoadingScreen
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.caseoverview.view.CaseOverviewViewState

@Composable
fun CaseOverviewScreen(
    state: CaseOverviewViewState,
    onCaseClick: (CaseOverviewModel) -> Unit,
) {

    val background = MaterialTheme.colorScheme.background


    when (state) {
        is CaseOverviewViewState.Error -> ErrorScreen()

        is CaseOverviewViewState.Loading -> LoadingScreen()

        is CaseOverviewViewState.Content -> {
            val items = state.caseOverviewItemList
            LazyColumn(modifier = Modifier.background(background)) {
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

@Preview
@Composable
fun CaseOverviewScreenPreview() {
    AppTheme {
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