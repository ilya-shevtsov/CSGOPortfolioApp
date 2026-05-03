package com.example.csgocaseswatcherapp.features.start.view

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.csgocaseswatcherapp.core.ui.BackgroundDecorations
import com.example.csgocaseswatcherapp.core.ui.DeviceConfigurationType
import com.example.csgocaseswatcherapp.core.ui.ErrorScreen
import com.example.csgocaseswatcherapp.core.ui.LoadingScreen
import com.example.csgocaseswatcherapp.core.ui.MainMenuButton
import com.example.csgocaseswatcherapp.core.ui.SmallButton
import com.example.csgocaseswatcherapp.core.ui.preview.PreviewPortraitLandscapeDarkLight
import com.example.csgocaseswatcherapp.core.ui.preview.PreviewScreenWithTopBar
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.start.R

@Composable
fun StartScreen(
    deviceConfigurationType: DeviceConfigurationType,
    state: StartViewState,
    onAction: (StartAction) -> Unit,
    modifier: Modifier = Modifier
) {
    when (state) {
        is StartViewState.Content -> {
            StartScreenContent(
                state = state,
                onAction = onAction,
                deviceConfigurationType = deviceConfigurationType,
                modifier = modifier
            )
        }

        is StartViewState.Error -> ErrorScreen()
        is StartViewState.Loading -> LoadingScreen()
    }
}

@Composable
private fun StartScreenContent(
    state: StartViewState.Content,
    onAction: (StartAction) -> Unit,
    deviceConfigurationType: DeviceConfigurationType,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        onAction(StartAction.OnCreate)
    }

    val isDark = isSystemInDarkTheme()

    val imageRes = if (isDark) {
        R.drawable.ic_frontpageimg_dark
    } else {
        R.drawable.ic_frontpageimg
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
//            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        when (deviceConfigurationType) {
            DeviceConfigurationType.MOBILE_PORTRAIT -> {
                StartScreenPortraitContent(
                    state = state,
                    onAction = onAction,
                    imageRes = imageRes
                )
            }

            DeviceConfigurationType.MOBILE_LANDSCAPE -> {
                StartScreenLandscapeContent(
                    state = state,
                    onAction = onAction,
                    imageRes = imageRes
                )
            }
        }
    }
}

@Composable
private fun StartScreenPortraitContent(
    state: StartViewState.Content,
    onAction: (StartAction) -> Unit,
    @DrawableRes imageRes: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = AppTheme.dimensions.paddingL),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderDecoration(
            currencyButtonText = state.currencyButton,
            onAction = onAction
        )

        LogoAndSlogan(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            imageResource = imageRes,
            sloganResource = R.string.front_page_slogan,
            imageModifier = Modifier.size(width = 240.dp, height = 210.dp)
        )

        ButtonsSelectionSection(
            onAction = onAction,
            modifier = Modifier
                .padding(bottom = AppTheme.dimensions.paddingXL)
                .weight(1.5f)
        )
    }
}

@Composable
private fun StartScreenLandscapeContent(
    state: StartViewState.Content,
    onAction: (StartAction) -> Unit,
    @DrawableRes imageRes: Int
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = AppTheme.dimensions.paddingL),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            BackgroundDecorations(modifier = Modifier.matchParentSize())

            LogoAndSlogan(
                modifier = Modifier.fillMaxSize(),
                imageResource = imageRes,
                sloganResource = R.string.front_page_slogan,
                imageModifier = Modifier.size(width = 250.dp, height = 210.dp),
                compact = true
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(
                    start = AppTheme.dimensions.paddingXL,
                    end = AppTheme.dimensions.paddingXL
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SmallButton(
                onClick = { onAction(StartAction.OnCurrencyChangeClicked) },
                buttonText = state.currencyButton,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = AppTheme.dimensions.paddingM, bottom = AppTheme.dimensions.paddingL)
            )

            Spacer(modifier = Modifier.height(AppTheme.dimensions.paddingM))

            ButtonsSelectionSection(
                onAction = onAction,
                modifier = Modifier.widthIn(max = 360.dp),
                compact = true
            )
        }
    }
}

@Composable
fun ButtonsSelectionSection(
    onAction: (StartAction) -> Unit,
    modifier: Modifier = Modifier,
    compact: Boolean = false
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 420.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(
                if (compact) 8.dp else 12.dp
            )
        ) {
            MainMenuButton(
                buttonText = stringResource(R.string.front_page_case_overview_button),
                onClick = { onAction(StartAction.OnCaseOverviewClicked) },
                modifier = Modifier.fillMaxWidth(),
                compact = compact
            )

            MainMenuButton(
                buttonText = stringResource(R.string.front_page_analytics_button),
                onClick = { onAction(StartAction.OnAnalyticsClicked) },
                modifier = Modifier.fillMaxWidth(),
                compact = compact
            )

            MainMenuButton(
                buttonText = stringResource(R.string.front_page_portfolio_button),
                onClick = { onAction(StartAction.OnPortfolioClicked) },
                modifier = Modifier.fillMaxWidth(),
                compact = compact
            )
        }
    }
}

@Composable
fun HeaderDecoration(
    modifier: Modifier = Modifier,
    currencyButtonText: String,
    onAction: (StartAction) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(96.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(start = AppTheme.dimensions.paddingM)
        ) {
            BackgroundDecorations(modifier = Modifier.matchParentSize())
        }

        SmallButton(
            modifier = Modifier.padding(top = AppTheme.dimensions.paddingM),
            onClick = { onAction(StartAction.OnCurrencyChangeClicked) },
            buttonText = currencyButtonText
        )
    }
}

@Composable
fun LogoAndSlogan(
    modifier: Modifier = Modifier,
    @DrawableRes imageResource: Int,
    @StringRes sloganResource: Int,
    imageModifier: Modifier = Modifier.size(width = 240.dp, height = 210.dp),
    compact: Boolean = false
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = null,
            modifier = imageModifier,
            alignment = Alignment.Center,
        )

        Spacer(
            modifier = Modifier.height(
                if (compact) AppTheme.dimensions.paddingM
                else AppTheme.dimensions.paddingM
            )
        )

        Text(
            text = stringResource(sloganResource),
            color = AppTheme.colors.onBackground,
            fontWeight = FontWeight.Bold,
            fontSize = if (compact) 14.sp else 16.sp,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = AppTheme.dimensions.paddingM)
        )
    }
}


@PreviewPortraitLandscapeDarkLight
@Composable
private fun StartScreenPreview() {
    PreviewScreenWithTopBar(
        title = "Welcome",
        canNavigateBack = false
    ) { deviceConfigurationType, paddingValues ->
        StartScreen(
            state = StartViewState.Content(currencyButton = "USD"),
            onAction = {},
            deviceConfigurationType = deviceConfigurationType,
            modifier = Modifier.padding(paddingValues)
        )
    }
}
