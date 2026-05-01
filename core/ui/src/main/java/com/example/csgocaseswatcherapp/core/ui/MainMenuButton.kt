package com.example.csgocaseswatcherapp.core.ui

import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme


@Composable
fun MainMenuButton(
    buttonText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    compact: Boolean = false
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .heightIn(min = if (compact) 48.dp else 72.dp),
        shape = AppTheme.shapes.buttonNormal,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colors.primary,
            contentColor = AppTheme.colors.onPrimary,
        ),
    ) {
        Text(
            text = buttonText,
            fontSize = if (compact) 14.sp else 18.sp,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@PreviewLightDark
@Composable
fun MainMenuButtonPreview() {
    AppTheme {
        MainMenuButton("Case Overview", {})
    }
}