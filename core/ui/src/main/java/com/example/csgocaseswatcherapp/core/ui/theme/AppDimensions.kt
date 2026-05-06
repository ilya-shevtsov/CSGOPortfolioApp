package com.example.csgocaseswatcherapp.core.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppDimensions(
    val paddingXSS: Dp = 2.dp,
    val paddingXS: Dp = 4.dp,
    val paddingS: Dp = 6.dp,
    val paddingM: Dp = 8.dp,
    val paddingML: Dp = 12.dp,
    val paddingL: Dp = 16.dp,
    val paddingXL: Dp = 24.dp,

    val appTopBarHorizontalPadding: Dp = 16.dp,
    val compactTopBarHeight: Dp = 40.dp,


    val backIconSize: Dp = 48.dp,

    val iconSmallSize: Dp = 18.dp,
    val iconMediumSize: Dp = 22.dp,

    val imageXSSize: Dp = 64.dp,
    val imageSmallSize: Dp = 88.dp,
    val imageMediumSize: Dp = 100.dp,
    val imageLargeSize: Dp = 160.dp,

    val imageCompactNarrowWidth: Dp = 72.dp,
    val imageCompactNarrowHeight: Dp = 56.dp,

    val imageNarrowWidth: Dp = 85.dp,
    val imageNarrowHeight: Dp = 66.dp,

    val shimmerTextFieldHeight: Dp = 22.dp,
    val shimmerTextFieldWidth: Dp = 150.dp,
    val shimmerTextFieldTitleWidth: Dp = 120.dp,

    val cardElevation: Dp = 2.dp,
    val tileElevation: Dp = 1.dp,

    val dropdownTonalElevation: Dp = 2.dp,
    val dropdownShadowElevation: Dp = 8.dp,

    val defaultDividerThickness: Dp = 2.dp
)

internal val LocalDimensions = staticCompositionLocalOf { AppDimensions() }
