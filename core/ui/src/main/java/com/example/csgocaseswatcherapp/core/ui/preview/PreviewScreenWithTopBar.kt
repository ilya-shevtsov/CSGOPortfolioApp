package com.example.csgocaseswatcherapp.core.ui.preview

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.example.csgocaseswatcherapp.core.ui.DeviceConfigurationType
import com.example.csgocaseswatcherapp.core.ui.rememberDeviceConfigurationType

@Composable
fun PreviewScreenWithTopBar(
    title: String,
    canNavigateBack: Boolean = false,
    content: @Composable (
        deviceConfigurationType: DeviceConfigurationType,
        paddingValues: PaddingValues
    ) -> Unit
) {
    val deviceConfigurationType = rememberDeviceConfigurationType()
    val isLandscape = deviceConfigurationType == DeviceConfigurationType.MOBILE_LANDSCAPE

    PreviewWithTopBar(
        title = title,
        canNavigateBack = canNavigateBack,
        isCompact = isLandscape
    ) { paddingValues ->
        content(deviceConfigurationType, paddingValues)
    }
}