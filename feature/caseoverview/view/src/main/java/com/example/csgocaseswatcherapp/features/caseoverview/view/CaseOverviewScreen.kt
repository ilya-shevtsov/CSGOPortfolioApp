package com.example.csgocaseswatcherapp.features.caseoverview.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.example.csgocaseswatcherapp.core.ui.DeviceConfigurationType
import com.example.csgocaseswatcherapp.core.ui.ErrorScreen
import com.example.csgocaseswatcherapp.core.ui.LoadingScreen
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.caseoverview.view.entities.CaseOverviewItem
import com.example.csgocaseswatcherapp.features.caseoverview.view.entities.CaseOverviewModel

@Composable
fun CaseOverviewScreen(
    state: CaseOverviewViewState,
    onCaseClick: (CaseOverviewModel) -> Unit,
    deviceConfigurationType: DeviceConfigurationType
) {
    when (state) {
        is CaseOverviewViewState.Error -> ErrorScreen()

        is CaseOverviewViewState.Loading -> LoadingScreen()

        is CaseOverviewViewState.Content -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppTheme.colors.background)
            ) {
                items(
                    items = state.caseOverviewItemList,
                    key = { item -> item.caseName }
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
    AppTheme {
        CaseOverviewScreen(
            state = CaseOverviewViewState.Content(
                caseOverviewItemList = listOf(
                    CaseOverviewModel(
                        caseName = "Recoil Case",
                        lowestPrice = 0.42,
                        volume = 24891,
                        medianPrice = 0.47,
                        imageUrl = "https://api.steamapis.com/image/item/730/Recoil%20Case",
                        releaseDate = "01.07.2022",
                        dropStatus = "Active",
                        description = "—"
                    ),
                    CaseOverviewModel(
                        caseName = "Fracture Case",
                        lowestPrice = 0.31,
                        volume = 18452,
                        medianPrice = 0.36,
                        imageUrl = "https://api.steamapis.com/image/item/730/Fracture%20Case",
                        releaseDate = "06.08.2020",
                        dropStatus = "Active",
                        description = "—"
                    ),
                    CaseOverviewModel(
                        caseName = "Snakebite Case",
                        lowestPrice = 0.28,
                        volume = 21337,
                        medianPrice = 0.33,
                        imageUrl = "https://api.steamapis.com/image/item/730/Snakebite%20Case",
                        releaseDate = "03.05.2021",
                        dropStatus = "Active",
                        description = "—"
                    ),
                    CaseOverviewModel(
                        caseName = "Operation Broken Fang Case",
                        lowestPrice = 5.84,
                        volume = 3921,
                        medianPrice = 6.15,
                        imageUrl = "https://api.steamapis.com/image/item/730/Operation%20Broken%20Fang%20Case",
                        releaseDate = "03.12.2020",
                        dropStatus = "Rare",
                        description = "—"
                    ),
                    CaseOverviewModel(
                        caseName = "Dreams & Nightmares Case",
                        lowestPrice = 1.12,
                        volume = 12408,
                        medianPrice = 1.24,
                        imageUrl = "https://api.steamapis.com/image/item/730/Dreams%20%26%20Nightmares%20Case",
                        releaseDate = "20.01.2022",
                        dropStatus = "Active",
                        description = "—"
                    )
                )
            ),
            onCaseClick = {},
            deviceConfigurationType = DeviceConfigurationType.MOBILE_PORTRAIT
        )
    }
}
