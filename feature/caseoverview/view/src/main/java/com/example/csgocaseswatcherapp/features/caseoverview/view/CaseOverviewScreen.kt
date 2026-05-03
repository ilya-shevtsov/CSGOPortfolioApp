package com.example.csgocaseswatcherapp.features.caseoverview.view

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.csgocaseswatcherapp.core.ui.ErrorScreen
import com.example.csgocaseswatcherapp.core.ui.preview.PreviewPortraitLandscapeDarkLight
import com.example.csgocaseswatcherapp.core.ui.preview.PreviewScreenWithTopBar
import com.example.csgocaseswatcherapp.core.ui.shimmer.ShimmerList
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.caseoverview.view.entities.CaseOverviewItem
import com.example.csgocaseswatcherapp.features.caseoverview.view.entities.CaseOverviewItemShimmer
import com.example.csgocaseswatcherapp.features.caseoverview.view.entities.CaseOverviewModel

@Composable
fun CaseOverviewScreen(
    state: CaseOverviewViewState,
    onCaseClick: (CaseOverviewModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedContent(
        modifier = modifier,
        targetState = state,
        transitionSpec = {
            fadeIn(
                animationSpec = tween(durationMillis = 260, delayMillis = 70)
            ) togetherWith fadeOut(
                animationSpec = tween(durationMillis = 220)
            ) using SizeTransform(clip = false)
        },
        label = "CaseOverviewScreenStateAnimation"
    ) { targetState ->
        when (targetState) {
            is CaseOverviewViewState.Error -> ErrorScreen()

            is CaseOverviewViewState.Loading -> CaseOverviewScreenShimmer()

            is CaseOverviewViewState.Content -> {
                CaseOverviewContent(
                    items = targetState.caseOverviewItemList,
                    onCaseClick = onCaseClick
                )
            }
        }
    }
}

@Composable
fun CaseOverviewScreenShimmer() {
    ShimmerList(
        itemCount = 6
    ) {
        CaseOverviewItemShimmer()
    }
}

@Composable
private fun CaseOverviewContent(
    items: List<CaseOverviewModel>,
    onCaseClick: (CaseOverviewModel) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
//        ,
//                contentPadding = PaddingValues(
//                horizontal = AppTheme.dimensions.paddingM,
//        vertical = AppTheme.dimensions.paddingM
//    )
    ) {
        items(
            items = items,
            key = { item -> item.caseName }
        ) { item ->
            CaseOverviewItem(
                item = item,
                onClick = { onCaseClick(item) },
                modifier = Modifier
            )
        }
    }
}

@PreviewPortraitLandscapeDarkLight
@Composable
private fun CaseOverviewScreenPreview() {
    PreviewScreenWithTopBar(
        title = "Case Overview",
        canNavigateBack = true
    ) { _, paddingValues ->
        CaseOverviewScreen(
            state = CaseOverviewViewState.Content(
                caseOverviewItemList = caseOverviewPreviewItems
            ),
            onCaseClick = {},
            modifier = Modifier.padding(paddingValues)
        )
    }
}

private val caseOverviewPreviewItems = listOf(
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
