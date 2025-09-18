package com.example.csgocaseswatcherapp.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme

@Composable
fun ErrorScreen(
    message: String? = null,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = AppTheme.colors.background,
        shadowElevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppTheme.dimensions.paddingXL),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_error),
                contentDescription = stringResource(R.string.errorIconDesc),
                tint = AppTheme.colors.error,
                modifier = Modifier
                    .size(48.dp)
                    .padding(bottom = 8.dp)
            )

            Text(
                text = message ?: stringResource(R.string.error_screen_case_overview),
                color = AppTheme.colors.error,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun ErrorScreenPreview() {
    AppTheme {
        ErrorScreen(
            null
        )
    }
}