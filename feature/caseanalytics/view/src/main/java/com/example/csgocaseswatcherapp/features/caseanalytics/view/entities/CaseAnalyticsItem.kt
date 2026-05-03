package com.example.csgocaseswatcherapp.features.caseanalytics.view.entities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.csgocaseswatcherapp.core.ui.DeviceConfigurationType
import com.example.csgocaseswatcherapp.core.ui.StatRow
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.caseanalytics.R
import com.example.csgocaseswatcherapp.ui.ExpandableStatSection
import com.example.csgocaseswatcherapp.core.ui.R as UiR

private data class AnalyticsStatUi(
    val icon: ImageVector,
    val label: String,
    val value: String
)

@Composable
fun CaseAnalyticsItem(
    item: CaseAnalyticsModel,
    deviceConfigurationType: DeviceConfigurationType,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.surface,
            contentColor = AppTheme.colors.onSurface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        when (deviceConfigurationType) {
            DeviceConfigurationType.MOBILE_PORTRAIT -> {
                CaseAnalyticsPortraitContent(item = item)
            }

            DeviceConfigurationType.MOBILE_LANDSCAPE -> {
                CaseAnalyticsLandscapeContent(item = item)
            }
        }
    }
}

@Composable
private fun CaseAnalyticsPortraitContent(
    item: CaseAnalyticsModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(AppTheme.dimensions.paddingM),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CaseImage(
            item = item,
            size = 88.dp,
            cornerRadius = 16.dp,
        )

        Spacer(Modifier.width(AppTheme.dimensions.paddingM))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.paddingM)
        ) {
            MainAnalyticsData(item = item)

            ExpandableStatSection(
                title = stringResource(R.string.expandable_stat_section_title)
            ) {
                SecondaryAnalyticsData(item = item)
            }
        }
    }
}

@Composable
private fun CaseAnalyticsLandscapeContent(
    item: CaseAnalyticsModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(AppTheme.dimensions.paddingL),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.paddingL)
    ) {
        LandscapeCaseHero(
            item = item,
            modifier = Modifier.weight(0.38f)
        )

        LandscapeAnalyticsDashboard(
            item = item,
            modifier = Modifier.weight(0.62f)
        )
    }
}

@Composable
private fun LandscapeCaseHero(
    item: CaseAnalyticsModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.paddingM),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CaseImage(
                item = item,
                size = 160.dp,
                cornerRadius = 24.dp,
                modifier = Modifier.sizeIn(
                    maxWidth = 180.dp,
                    maxHeight = 180.dp
                )
            )

            Text(
                text = item.caseName,
                style = MaterialTheme.typography.titleLarge,
                color = AppTheme.colors.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            HighlightStatPill(
                label = stringResource(R.string.monthly_avg_return),
                value = item.monthlyAvgReturnInPercent
            )

            Text(
                text = stringResource(
                    R.string.monthly_sharpe_value,
                    item.monthlySharpRatio
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = AppTheme.colors.onSurface.copy(alpha = 0.72f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun HighlightStatPill(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(100.dp),
        color = AppTheme.colors.primary.copy(alpha = 0.12f)
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = AppTheme.dimensions.paddingML,
                vertical = AppTheme.dimensions.paddingM
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.paddingM)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.TrendingUp,
                contentDescription = null,
                tint = AppTheme.colors.primary,
                modifier = Modifier.size(18.dp)
            )

            Text(
                text = "$label: $value",
                style = MaterialTheme.typography.labelLarge,
                color = AppTheme.colors.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun LandscapeAnalyticsDashboard(
    item: CaseAnalyticsModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        AnalyticsStatGroup(
            title = stringResource(R.string.analytics_stat_group_title_monthly),
            stats = listOf(
                AnalyticsStatUi(
                    icon = Icons.Outlined.CalendarToday,
                    label = stringResource(R.string.monthly_avg_return),
                    value = item.monthlyAvgReturnInPercent
                ),
                AnalyticsStatUi(
                    icon = Icons.Outlined.QueryStats,
                    label = stringResource(R.string.monthly_volatility_std),
                    value = item.monthlyStandardDeviation
                ),
                AnalyticsStatUi(
                    icon = Icons.Outlined.CurrencyRuble,
                    label = stringResource(R.string.monthly_avg_return_rub),
                    value = item.monthlyAvgReturnInRUB
                ),
                AnalyticsStatUi(
                    icon = Icons.Outlined.Scale,
                    label = stringResource(R.string.monthly_sharpe_ratio),
                    value = item.monthlySharpRatio
                )
            )
        )
        AnalyticsStatGroup(
            title = stringResource(R.string.analytics_stat_group_title_daily),
            stats = listOf(
                AnalyticsStatUi(
                    icon = Icons.AutoMirrored.Outlined.TrendingUp,
                    label = stringResource(R.string.avg_return),
                    value = item.dailyAvgReturnInPercent
                ),
                AnalyticsStatUi(
                    icon = Icons.Outlined.QueryStats,
                    label = stringResource(R.string.volatility_std),
                    value = item.dailyStandardDeviation
                ),
                AnalyticsStatUi(
                    icon = Icons.Outlined.CurrencyRuble,
                    label = stringResource(R.string.avg_return_rub),
                    value = item.dailyAvgReturnInRUB
                ),
                AnalyticsStatUi(
                    icon = Icons.Outlined.Scale,
                    label = stringResource(R.string.sharpe_ratio),
                    value = item.dailySharpRatio
                )
            )
        )
    }
}

@Composable
private fun AnalyticsStatGroup(
    title: String,
    stats: List<AnalyticsStatUi>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.paddingM)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
            color = AppTheme.colors.onSurface.copy(alpha = 0.72f)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.paddingM),
        ) {
            AnalyticsStatColumn(
                stats = stats.take(2),
                modifier = Modifier.weight(1f)
            )
            AnalyticsStatColumn(
                stats = stats.drop(2),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun AnalyticsStatColumn(
    stats: List<AnalyticsStatUi>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.paddingM)
    ) {
        stats.forEach { stat ->
            CompactStatTile(
                icon = stat.icon,
                label = stat.label,
                value = stat.value
            )
        }
    }
}

@Composable
private fun CompactStatTile(
    icon: ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = AppTheme.colors.background.copy(alpha = 0.55f),
        tonalElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AppTheme.dimensions.paddingML, vertical = AppTheme.dimensions.paddingM),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = AppTheme.colors.primary,
                modifier = Modifier.size(20.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelSmall,
                    color = AppTheme.colors.onSurface.copy(alpha = 0.65f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = value,
                    style = MaterialTheme.typography.titleSmall,
                    color = AppTheme.colors.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun CaseImage(
    item: CaseAnalyticsModel,
    size: Dp,
    cornerRadius: Dp,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(item.imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = item.caseName,
        error = painterResource(UiR.drawable.ic_error),
        placeholder = painterResource(UiR.drawable.case_placeholder),
        contentScale = ContentScale.Fit,
        modifier = modifier
            .size(size)
            .clip(RoundedCornerShape(cornerRadius))
    )
}

@Composable
fun MainAnalyticsData(
    item: CaseAnalyticsModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.paddingM)
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
            label = stringResource(R.string.monthly_avg_return),
            value = item.monthlyAvgReturnInPercent,
        )
        StatRow(
            icon =
                Icons.Outlined.CurrencyRuble,
            label = stringResource(R.string.monthly_avg_return_rub),
            value = item.monthlyAvgReturnInRUB,
        )
        StatRow(
            icon =
                Icons.Outlined.QueryStats,
            label = stringResource(R.string.monthly_volatility_std),
            value = item.monthlyStandardDeviation
        )
        StatRow(
            icon =
                Icons.Outlined.Scale,
            label = stringResource(R.string.monthly_sharpe_ratio),
            value = item.monthlySharpRatio
        )
    }
}

@Composable
fun SecondaryAnalyticsData(item: CaseAnalyticsModel) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        StatRow(
            icon = Icons.AutoMirrored.Outlined.TrendingUp,
            label = stringResource(R.string.avg_return),
            value = item.dailyAvgReturnInPercent,
        )
        StatRow(
            icon = Icons.Outlined.CurrencyRuble,
            label = stringResource(R.string.avg_return_rub),
            value = item.dailyAvgReturnInRUB,
        )
        StatRow(
            icon = Icons.Outlined.QueryStats,
            label = stringResource(R.string.volatility_std),
            value = item.dailyStandardDeviation
        )
        StatRow(
            icon = Icons.Outlined.Scale,
            label = stringResource(R.string.sharpe_ratio),
            value = item.dailySharpRatio
        )
    }

}


@PreviewLightDark
@Composable
private fun CaseAnalyticsItemPreview() {
    AppTheme {
        CaseAnalyticsItem(
            item = mockItem,
            deviceConfigurationType = DeviceConfigurationType.MOBILE_PORTRAIT
        )
    }
}

@Preview(
    widthDp = 923,
    heightDp = 411,
    showBackground = true
)
@Composable
fun CaseAnalyticsItemLandscapeDarkPreview() {
    AppTheme(darkTheme = true) {
        CaseAnalyticsItem(
            item = mockItem,
            deviceConfigurationType = DeviceConfigurationType.MOBILE_LANDSCAPE
        )
    }
}

val mockItem = CaseAnalyticsModel(
    caseName = "Chroma Case",
    dailyAvgReturnInPercent = "0.14 %",
    dailyAvgReturnInRUB = "-0.31",
    dailyStandardDeviation = "0.06421",
    dailySharpRatio = "0.03216",
    monthlyAvgReturnInPercent = "4.11 %",
    monthlyAvgReturnInRUB = "-3.24",
    monthlyStandardDeviation = "0.22929",
    monthlySharpRatio = "0.21576",
    imageUrl = "https://api.steamapis.com/image/item/730/Chroma%20Case"
)