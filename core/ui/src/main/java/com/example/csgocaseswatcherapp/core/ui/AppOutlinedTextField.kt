package com.example.csgocaseswatcherapp.core.ui

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme

@Composable
fun AppOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    error: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = true,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = { Text(label) },
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        isError = error != null,
        supportingText = {
            error?.let {
                Text(
                    text = it,
                    color = AppTheme.colors.error,
                    style = AppTheme.typography.m3.bodySmall
                )
            }
        },
        colors = appOutlinedTextFieldColors()
    )
}

@Composable
fun appOutlinedTextFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedTextColor = AppTheme.colors.onSurface,
    unfocusedTextColor = AppTheme.colors.onSurface,

    cursorColor = AppTheme.colors.primary,

    focusedBorderColor = AppTheme.colors.primary,
    unfocusedBorderColor = AppTheme.colors.onSurface.copy(alpha = 0.30f),

    focusedLabelColor = AppTheme.colors.primary,
    unfocusedLabelColor = AppTheme.colors.onSurface.copy(alpha = 0.60f),

    errorTextColor = AppTheme.colors.onSurface,
    errorBorderColor = AppTheme.colors.error,
    errorLabelColor = AppTheme.colors.error,
    errorCursorColor = AppTheme.colors.error,
)