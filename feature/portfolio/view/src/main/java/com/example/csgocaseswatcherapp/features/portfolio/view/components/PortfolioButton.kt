package com.example.csgocaseswatcherapp.features.portfolio.view.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme

@Composable
fun PortfolioButton(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    compact: Boolean = false
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(
            if (compact) 40.dp else 44.dp
        ),
        shape = AppTheme.shapes.buttonRounded,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colors.primary,
            contentColor = AppTheme.colors.onPrimary
        ),
        contentPadding = PaddingValues(
            horizontal = if (compact) AppTheme.dimensions.paddingM else AppTheme.dimensions.paddingML,
        )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(if (compact) AppTheme.dimensions.iconSmallSize else AppTheme.dimensions.iconMediumSize)
        )

        Spacer(Modifier.width(if (compact) AppTheme.dimensions.paddingXS else AppTheme.dimensions.paddingS))

        Text(
            text = text,
            fontSize = 12.sp,
            maxLines = 1
        )
    }
}