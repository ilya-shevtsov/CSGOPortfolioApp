package com.example.csgocaseswatcherapp.features.start.view

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.core.ui.MainMenuButton
import com.example.csgocaseswatcherapp.core.ui.SmallButton


@Composable
fun StartScreen(
    state: StartViewState.Content,
    onCaseOverviewClicked: () -> Unit,
    onCaseAnalyticsClicked: () -> Unit,
    onPortfolioClicked: () -> Unit,
    onCurrencyClicked: () -> Unit
) {
    val primary = MaterialTheme.colorScheme.primary
    val background = MaterialTheme.colorScheme.background

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            HeaderDecoration(
                primaryColor = primary,
                currencyButtonText = state.currencyButton,
                onCurrencyClicked = onCurrencyClicked
            )

            LogoAndSlogan(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                imageResource = R.drawable.ic_frontpageimg,
                sloganResource = R.string.front_page_slogan
            )

            ButtonsSelectionSection(
                onCaseOverviewClicked = onCaseOverviewClicked,
                onCaseAnalyticsClicked = onCaseAnalyticsClicked,
                onPortfolioClicked = onPortfolioClicked
            )
            Spacer(modifier = Modifier.size(80.dp))
        }
    }
}

@Composable
fun ButtonsSelectionSection(
    onCaseOverviewClicked: () -> Unit,
    onCaseAnalyticsClicked: () -> Unit,
    onPortfolioClicked: () -> Unit,
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
                .padding(bottom = 20.dp)
        ) {
            MainMenuButton(
                buttonText = stringResource(R.string.front_page_case_overview_button),
                onClick = onCaseOverviewClicked
            )
            MainMenuButton(
                buttonText = stringResource(R.string.front_page_analytics_button),
                onClick = onCaseAnalyticsClicked
            )
            MainMenuButton(
                buttonText = stringResource(R.string.front_page_portfolio_button),
                onClick = onPortfolioClicked
            )
        }
    }
}

@Composable
fun HeaderDecoration(
    modifier: Modifier = Modifier,
    primaryColor: Color,
    currencyButtonText: String,
    onCurrencyClicked: () -> Unit
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
            Canvas(modifier = modifier.matchParentSize()) {
                val r = 80.dp.toPx()
                drawCircle(
                    color = primaryColor,
                    radius = r,
                    center = Offset(r * 0.75f, r * 0.05f),
                    alpha = 0.25f
                )
                drawCircle(
                    color = primaryColor,
                    radius = r,
                    center = Offset(r * -0.1f, r * 0.75f),
                    alpha = 0.25f
                )
            }
        }
        SmallButton(
            modifier = Modifier.padding(top = 12.dp, end = 12.dp),
            onClick = onCurrencyClicked,
            buttonText = currencyButtonText
        )
    }
}

@Composable
fun LogoAndSlogan(
    modifier: Modifier = Modifier,
    @DrawableRes imageResource: Int,
    @StringRes sloganResource: Int,
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
                modifier = Modifier.size(width = 280.dp, height = 240.dp),
                alignment = Alignment.Center,
            )
            Spacer(modifier = Modifier.size(50.dp))
            Text(
                text = stringResource(sloganResource),
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}

@Preview
@Composable
fun StartScreenPreview() {
    com.example.csgocaseswatcherapp.core.ui.theme.AppTheme(
        darkTheme = false,
        dynamicColor = false
    ) {
        StartScreen(
            StartViewState.Content(currencyButton = "RUB"),
            onCaseOverviewClicked = {},
            onCaseAnalyticsClicked = {},
            onPortfolioClicked = {},
            onCurrencyClicked = {},
        )
    }
}