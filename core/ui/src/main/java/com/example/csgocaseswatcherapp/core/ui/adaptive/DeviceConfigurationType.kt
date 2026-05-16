package com.example.csgocaseswatcherapp.core.ui.adaptive

import androidx.window.core.layout.WindowSizeClass

enum class DeviceConfigurationType {
    MOBILE_PORTRAIT,
    MOBILE_LANDSCAPE;

    companion object {
        fun fromWindowSizeClass(windowSizeClass: WindowSizeClass): DeviceConfigurationType {
            val isExpandedWidth = windowSizeClass.isWidthAtLeastBreakpoint(840)
            val isCompactHeight = !windowSizeClass.isHeightAtLeastBreakpoint(480)

            return when {
                isExpandedWidth && isCompactHeight -> MOBILE_LANDSCAPE
                else -> MOBILE_PORTRAIT
            }
        }
    }
}