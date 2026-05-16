package com.example.csgocaseswatcherapp.core.ui.adaptive

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable

@Composable
fun rememberDeviceConfigurationType(): DeviceConfigurationType {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    return DeviceConfigurationType.fromWindowSizeClass(windowSizeClass)
}