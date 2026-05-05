package com.example.csgocaseswatcherapp.features.portfolio.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.csgocaseswatcherapp.core.ui.CaseImage
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.portfolio.R
import com.example.csgocaseswatcherapp.features.portfolio.view.formatSignedPercent
import com.example.csgocaseswatcherapp.features.portfolio.view.formatSignedUsd
import com.example.csgocaseswatcherapp.features.portfolio.view.formatUsd
import com.example.csgocaseswatcherapp.features.portfolio.view.model.PortfolioItemModel

@Composable
fun PortfolioItemCard(
    item: PortfolioItemModel, modifier: Modifier = Modifier, compact: Boolean = false
) {
    val imageWidth = if (compact) {
        AppTheme.dimensions.imageCompactNarrowWidth
    } else {
        AppTheme.dimensions.imageNarrowWidth
    }

    val imageHeight = if (compact) {
        AppTheme.dimensions.imageCompactNarrowHeight
    } else {
        AppTheme.dimensions.imageNarrowHeight
    }

    val cardPadding = if (compact) {
        AppTheme.dimensions.paddingM
    } else {
        AppTheme.dimensions.paddingML
    }
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.surface, contentColor = AppTheme.colors.onSurface
        ),
        shape = AppTheme.shapes.narrowCard,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(cardPadding), verticalAlignment = Alignment.CenterVertically
        ) {
            CaseImage(
                imageUrl = item.itemImage,
                width = imageWidth,
                height = imageHeight,
                clipShape = AppTheme.shapes.imageClip
            )

            Spacer(modifier = Modifier.width(if (compact) AppTheme.dimensions.paddingM else AppTheme.dimensions.paddingML))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.itemName, style = if (compact) {
                        MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    } else {
                        MaterialTheme.typography.titleMedium
                    }, maxLines = if (compact) 1 else 2, color = AppTheme.colors.onSurface
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = stringResource(
                        id = R.string.portfolio_amount_price, pluralStringResource(
                            id = R.plurals.portfolio_cases_count, count = item.amount, item.amount
                        ), formatUsd(item.price)
                    ), style = if (compact) {
                        MaterialTheme.typography.bodySmall
                    } else {
                        MaterialTheme.typography.bodyMedium
                    }, maxLines = 1, color = AppTheme.colors.onSurface
                )
            }

            Spacer(modifier = Modifier.width(if (compact) AppTheme.dimensions.paddingM else AppTheme.dimensions.paddingML))

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = formatUsd(item.totalValue), style = if (compact) {
                        MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    } else {
                        MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    }, color = AppTheme.colors.onSurface, maxLines = 1
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = stringResource(
                        id = R.string.portfolio_profit_loss,
                        formatSignedUsd(item.profitLoss),
                        formatSignedPercent(item.profitLossPercent)
                    ), style = MaterialTheme.typography.bodySmall, color = AppTheme.colors.onSurface, maxLines = 1
                )
            }
        }
    }
}