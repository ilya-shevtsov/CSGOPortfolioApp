package com.example.csgocaseswatcherapp.features.start.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.core.ui.MainMenuButton


@Composable
fun StartScreen(
    state: StartViewState.Content,
    onCaseOverviewClicked: () -> Unit,
    onCaseAnalyticsClicked: () -> Unit,
    onPortfolioClicked: () -> Unit,
    onCurrencyClicked: () -> Unit
) {
    val primary = colorResource(R.color.primaryColor)
    val bg = colorResource(R.color.backgroundColor)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    Canvas(Modifier.fillMaxSize()) {
                        val r = 80.dp.toPx()
                        drawCircle(
                            color = primary,
                            radius = r,
                            center = Offset(r * 0.75f, r * 0.05f),
                            alpha = 0.25f
                        )
                        drawCircle(
                            color = primary,
                            radius = r,
                            center = Offset(r * -0.1f, r * 0.75f),
                            alpha = 0.25f
                        )
                    }
                }

                Button(
                    onClick = onCurrencyClicked,
                    modifier = Modifier
                        .padding(top = 12.dp, end = 12.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = primary,
                        contentColor = Color.White
                    ),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text(state.currencyButton)
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_frontpageimg),
                        contentDescription = null,
                        modifier = Modifier.size(width = 280.dp, height = 240.dp),
                        alignment = Alignment.Center,
                    )
                    Spacer(modifier = Modifier.size(50.dp))
                    Text(
                        text = stringResource(R.string.front_page_slogan),
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
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
            Spacer(modifier = Modifier.size(80.dp))
        }
    }
}


@Composable
@Preview
fun StartScreenPreview() {
    StartScreen(
        StartViewState.Content(currencyButton = "RUB"),
        onCaseOverviewClicked = {},
        onCaseAnalyticsClicked = {},
        onPortfolioClicked = {},
        onCurrencyClicked = {},
    )
}