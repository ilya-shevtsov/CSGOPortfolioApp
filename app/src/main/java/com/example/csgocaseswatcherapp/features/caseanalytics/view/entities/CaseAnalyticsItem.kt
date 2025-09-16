package com.example.csgocaseswatcherapp.features.caseanalytics.view.entities

import ExpandableStatSection
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.TrendingUp
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.CurrencyRuble
import androidx.compose.material.icons.outlined.QueryStats
import androidx.compose.material.icons.outlined.Scale
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.caseoverview.view.entities.StatRow
import kotlin.math.pow
import kotlin.math.round

@Composable
fun CaseAnalyticsItem(
    item: CaseAnalyticsModel,
    onClick: () -> Unit,
) {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(AppTheme.dimensions.paddingML)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.surface,
            contentColor = AppTheme.colors.onSurface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimensions.paddingM),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = item.caseName,
                placeholder = painterResource(R.drawable.case_placeholder),
                error = painterResource(R.drawable.ic_error),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Spacer(Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = item.caseName,
                    style = MaterialTheme.typography.titleMedium,
                    color = AppTheme.colors.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                StatRow(
                    icon = Icons.Outlined.CalendarToday,
                    label = "Monthly Avg Return",
                    value = "${item.monthlyAvgReturnInPercent} %",
                )
                StatRow(
                    icon =
                    Icons.Outlined.CurrencyRuble,
                    label = "Monthly Avg Return (₽)", value = "${item.monthlyAvgReturnInRUB}",
                )
                StatRow(
                    icon =
                    Icons.Outlined.QueryStats,
                    label = "Monthly Volatility (Std)",
                    value = "${item.monthlyStandardDeviation.toDoubleWith5Decimals()}"
                )
                StatRow(
                    icon =
                    Icons.Outlined.Scale,
                    label = "Monthly Sharpe Ratio",
                    value = "${item.monthlySharpRatio.toDoubleWith5Decimals()}"
                )

                ExpandableStatSection(title = "Daily Stats") {
                    StatRow(
                        icon = Icons.AutoMirrored.Outlined.TrendingUp,
                        label = "Avg Return",
                        value = "${item.dailyAvgReturnInPercent} %",
                    )
                    StatRow(
                        icon = Icons.Outlined.CurrencyRuble,
                        label = "Avg Return (₽)",
                        value = "${item.dailyAvgReturnInRUB}",
                    )
                    StatRow(
                        icon = Icons.Outlined.QueryStats,
                        label = "Volatility (Std)",
                        value = item.dailyStandardDeviation.toDoubleWith5Decimals().toString()
                    )
                    StatRow(
                        icon = Icons.Outlined.Scale,
                        label = "Sharpe Ratio",
                        value = item.dailySharpRatio.toDoubleWith5Decimals().toString()
                    )
                }
            }
        }
    }
}

fun Double.toDoubleWith5Decimals(): Double {
    val factor = 10.0.pow(5)
    return round(this * factor) / factor
}

@PreviewLightDark
@Composable
private fun CaseAnalyticsItemPreview() {
    AppTheme {
        CaseAnalyticsItem(
            item = CaseAnalyticsModel(
                caseName = "Chroma Case",
                dailyAvgReturnInPercent = 0.14,
                dailyAvgReturnInRUB = -0.31,
                dailyStandardDeviation = 0.06421299942865188,
                dailySharpRatio = 0.03216030151453114,
                monthlyAvgReturnInPercent = 4.11,
                monthlyAvgReturnInRUB = -3.24,
                monthlyStandardDeviation = 0.22929070765645318,
                monthlySharpRatio = 0.21576985108546862,
                imageUrl = "https://api.steamapis.com/image/item/730/Chroma%20Case"
            ),
            onClick = {}
        )
    }
}