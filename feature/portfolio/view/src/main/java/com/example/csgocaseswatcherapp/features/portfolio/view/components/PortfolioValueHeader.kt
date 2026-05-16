package com.example.csgocaseswatcherapp.features.portfolio.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.portfolio.R
import com.example.csgocaseswatcherapp.features.portfolio.view.formatUsd

@Composable
fun PortfolioValueHeader(
    totalPortfolioValue: Double,
    modifier: Modifier
) {

    val totalPortfolioValueText = stringResource(
        id = R.string.portfolio_total_value,
        formatUsd(totalPortfolioValue)
    )
    Column(modifier = modifier) {
        Text(
            text = totalPortfolioValueText,
            color = AppTheme.colors.onBackground,
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            ),
            maxLines = 1,
        )
    }
}