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
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.csgocaseswatcherapp.core.ui.BackgroundDecorations
import com.example.csgocaseswatcherapp.core.ui.DeviceConfiguration
import com.example.csgocaseswatcherapp.core.ui.ErrorScreen
import com.example.csgocaseswatcherapp.core.ui.LoadingScreen
import com.example.csgocaseswatcherapp.core.ui.MainMenuButton
import com.example.csgocaseswatcherapp.core.ui.SmallButton
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.start.R


@Composable
fun StartScreen(
    state: StartViewState,
    onAction: (StartAction) -> Unit,
) {
    when (state) {
        is StartViewState.Content ->
            StartScreenContent(
                state = state,
                onAction = onAction,
            )

        is StartViewState.Error -> ErrorScreen()
        is StartViewState.Loading -> LoadingScreen()
    }
}

@Composable
private fun StartScreenContent(
    state: StartViewState.Content,
    onAction: (StartAction) -> Unit
) {

    LaunchedEffect(Unit) {
        onAction(StartAction.OnCreate)
    }

    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)

    val isDark = isSystemInDarkTheme()

    val imageRes = if (isDark) {
        R.drawable.ic_frontpageimg_dark
    } else {
        R.drawable.ic_frontpageimg
    }

    when (deviceConfiguration) {
        DeviceConfiguration.MOBILE_PORTRAIT -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppTheme.colors.background)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = AppTheme.dimensions.paddingL)
                ) {
                    HeaderDecoration(
                        currencyButtonText = state.currencyButton,
                        onAction = { onAction(StartAction.OnCurrencyChangeClicked) }
                    )

                    LogoAndSlogan(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        imageResource = imageRes,
                        sloganResource = R.string.front_page_slogan,
                        imageModifier = Modifier.size(width = 280.dp, height = 240.dp)
                    )

                    ButtonsSelectionSection(
                        onAction = onAction
                    )
                    Spacer(modifier = Modifier.size(80.dp))
                }
            }
        }

        DeviceConfiguration.MOBILE_LANDSCAPE -> {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppTheme.colors.background)
                    .padding(AppTheme.dimensions.paddingL),
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
                        imageModifier = Modifier.size(width = 320.dp, height = 280.dp)
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(horizontal = AppTheme.dimensions.paddingL),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    SmallButton(
                        onClick = { onAction(StartAction.OnCurrencyChangeClicked) },
                        buttonText = state.currencyButton,
                        modifier = Modifier.align(Alignment.End)
                    )

                    Spacer(modifier = Modifier.height(AppTheme.dimensions.paddingL))

                    ButtonsSelectionSection(
                        onAction = onAction,
                        modifier = Modifier.widthIn(max = 420.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun ButtonsSelectionSection(
    onAction: (StartAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            MainMenuButton(
                buttonText = stringResource(R.string.front_page_case_overview_button),
                onClick = { onAction(StartAction.OnCaseOverviewClicked) },
            )
            MainMenuButton(
                buttonText = stringResource(R.string.front_page_analytics_button),
                onClick = { onAction(StartAction.OnAnalyticsClicked) },

                )
            MainMenuButton(
                buttonText = stringResource(R.string.front_page_portfolio_button),
                onClick = { onAction(StartAction.OnPortfolioClicked) },
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
            .height(140.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            BackgroundDecorations(modifier = Modifier.matchParentSize())
        }
        SmallButton(
            modifier = Modifier.padding(
                top = AppTheme.dimensions.paddingML,
                end = AppTheme.dimensions.paddingML
            ),
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
    imageModifier: Modifier = Modifier.size(width = 280.dp, height = 240.dp),
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = null,
                modifier = imageModifier,
                alignment = Alignment.Center,
            )

            Spacer(modifier = Modifier.size(AppTheme.dimensions.paddingL))

            Text(
                text = stringResource(sloganResource),
                color = AppTheme.colors.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@PreviewLightDark
@Composable
fun StartScreenPreview() {
    AppTheme {
        StartScreen(
            state = StartViewState.Content(currencyButton = "RUB"),
            onAction = {}
        )
    }
}