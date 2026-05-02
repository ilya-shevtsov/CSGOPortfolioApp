package com.example.csgocaseswatcherapp.features.caseanalytics.view.entities

import ExpandableStatSection
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.csgocaseswatcherapp.core.ui.DeviceConfigurationType
import com.example.csgocaseswatcherapp.core.ui.StatRow
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.caseanalytics.R
import com.example.csgocaseswatcherapp.core.ui.R as UiR

@Composable
fun CaseAnalyticsItem(
    item: CaseAnalyticsModel,
    deviceConfigurationType: DeviceConfigurationType
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(AppTheme.dimensions.paddingML),
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
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            when (deviceConfigurationType) {
                DeviceConfigurationType.MOBILE_PORTRAIT -> {
                    CaseImage(item = item)
                    Spacer(Modifier.width(16.dp))
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        MainAnalyticsData(item = item)

                        ExpandableStatSection(title = stringResource(R.string.expandable_stat_section_title)) {
                            SecondaryAnalyticsData(item = item)
                        }
                    }
                }

                DeviceConfigurationType.MOBILE_LANDSCAPE -> {
                    CaseImage(item = item)
                    Spacer(Modifier.width(16.dp))
                    MainAnalyticsData(item = item)
                    Spacer(Modifier.width(16.dp))
                    SecondaryAnalyticsData(item = item)
                }
            }
        }
    }
}

@Composable
fun CaseImage(item: CaseAnalyticsModel) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(item.imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = item.caseName,
        error = painterResource(UiR.drawable.ic_error),
        placeholder = painterResource(UiR.drawable.case_placeholder),
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}

@Composable
fun MainAnalyticsData(item: CaseAnalyticsModel) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
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
    widthDp = 800,
    heightDp = 200,
    showBackground = true
)
@Composable
fun CaseAnalyticsItemLandscapeLightPreview() {
    AppTheme(darkTheme = false) {
        CaseAnalyticsItem(
            item = mockItem,
            deviceConfigurationType = DeviceConfigurationType.MOBILE_LANDSCAPE
        )
    }
}

@Preview(
    widthDp = 800,
    heightDp = 200,
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