package com.example.csgocaseswatcherapp.core.ui

import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

enum class DeviceConfiguration {
    MOBILE_PORTRAIT,
    MOBILE_LANDSCAPE;

    companion object {
        fun fromWindowSizeClass(windowSizeClass: WindowSizeClass): DeviceConfiguration {
            val widthClass = windowSizeClass.windowWidthSizeClass
            val heightClass = windowSizeClass.windowHeightSizeClass

            return when {
                widthClass == WindowWidthSizeClass.COMPACT &&
                        heightClass == WindowHeightSizeClass.MEDIUM -> MOBILE_PORTRAIT

                widthClass == WindowWidthSizeClass.COMPACT &&
                        heightClass == WindowHeightSizeClass.EXPANDED -> MOBILE_PORTRAIT

                widthClass == WindowWidthSizeClass.EXPANDED &&
                        heightClass == WindowHeightSizeClass.COMPACT -> MOBILE_LANDSCAPE

                else -> MOBILE_PORTRAIT //TODO: stub for tablet & desktop

            }
        }
    }
}