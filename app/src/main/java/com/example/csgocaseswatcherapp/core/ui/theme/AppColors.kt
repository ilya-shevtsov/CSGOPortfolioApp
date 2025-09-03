package com.example.csgocaseswatcherapp.core.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class AppColors(
    primary: Color,
    onPrimary: Color,
    primaryContainer: Color,
    onPrimaryContainer: Color,
    secondary: Color,
    onSecondary: Color,
    secondaryContainer: Color,
    onSecondaryContainer: Color,
    background: Color,
    onBackground: Color,
    surface: Color,
    onSurface: Color,
    error: Color,
    onError: Color,
) {
    var primary by mutableStateOf(primary); private set
    var onPrimary by mutableStateOf(onPrimary); private set
    var primaryContainer by mutableStateOf(primaryContainer); private set
    var onPrimaryContainer by mutableStateOf(onPrimaryContainer); private set

    var secondary by mutableStateOf(secondary); private set
    var onSecondary by mutableStateOf(onSecondary); private set
    var secondaryContainer by mutableStateOf(secondaryContainer); private set
    var onSecondaryContainer by mutableStateOf(onSecondaryContainer); private set

    var background by mutableStateOf(background); private set
    var onBackground by mutableStateOf(onBackground); private set

    var surface by mutableStateOf(surface); private set
    var onSurface by mutableStateOf(onSurface); private set

    var error by mutableStateOf(error); private set
    var onError by mutableStateOf(onError); private set

    fun copy(
        primary: Color = this.primary,
        onPrimary: Color = this.onPrimary,
        primaryContainer: Color = this.primaryContainer,
        onPrimaryContainer: Color = this.onPrimaryContainer,
        secondary: Color = this.secondary,
        onSecondary: Color = this.onSecondary,
        secondaryContainer: Color = this.secondaryContainer,
        onSecondaryContainer: Color = this.onSecondaryContainer,
        background: Color = this.background,
        onBackground: Color = this.onBackground,
        surface: Color = this.surface,
        onSurface: Color = this.onSurface,
        error: Color = this.error,
        onError: Color = this.onError,
    ) = AppColors(
        primary, onPrimary, primaryContainer, onPrimaryContainer,
        secondary, onSecondary, secondaryContainer, onSecondaryContainer,
        background, onBackground, surface, onSurface, error, onError
    )

    fun updateFrom(other: AppColors) {
        primary = other.primary
        onPrimary = other.onPrimary
        primaryContainer = other.primaryContainer
        onPrimaryContainer = other.onPrimaryContainer

        secondary = other.secondary
        onSecondary = other.onSecondary
        secondaryContainer = other.secondaryContainer
        onSecondaryContainer = other.onSecondaryContainer

        background = other.background
        onBackground = other.onBackground

        surface = other.surface
        onSurface = other.onSurface

        error = other.error
        onError = other.onError
    }
}

fun appColorsFrom(scheme: ColorScheme) = AppColors(
    primary = scheme.primary,
    onPrimary = scheme.onPrimary,
    primaryContainer = scheme.primaryContainer,
    onPrimaryContainer = scheme.onPrimaryContainer,
    secondary = scheme.secondary,
    onSecondary = scheme.onSecondary,
    secondaryContainer = scheme.secondaryContainer,
    onSecondaryContainer = scheme.onSecondaryContainer,
    background = scheme.background,
    onBackground = scheme.onBackground,
    surface = scheme.surface,
    onSurface = scheme.onSurface,
    error = scheme.error,
    onError = scheme.onError,
)

fun lightColors(): AppColors = AppColors(
    primary = Color(0xFF40BBD6),
    onPrimary = Color.White,
    primaryContainer = Color(0xFF7CEEFF),
    onPrimaryContainer = Color(0xFF008BA5),
    secondary = Color(0xFFE5F7FF),
    onSecondary = Color.Black,
    secondaryContainer = Color.White,
    onSecondaryContainer = Color(0xFFB3C4CC),
    background = Color(0xFFE5F7FF),
    onBackground = Color.Black,
    surface = Color(0xFFEDFaff),
    onSurface = Color.Black,
    error = Color.Red,
    onError = Color.White,
)

fun darkColors(): AppColors = AppColors(
    primary = Color(0xFF40BBD6),
    onPrimary = Color.Black,
    primaryContainer = Color(0xFF006876),
    onPrimaryContainer = Color(0xFF9CF0FF),
    secondary = Color(0xFF1C2A30),
    onSecondary = Color(0xFFE5F7FF),
    secondaryContainer = Color(0xFF2E3F46),
    onSecondaryContainer = Color(0xFFB3C4CC),
    background = Color(0xFF121212),
    onBackground = Color(0xFFB3C4CC),
    surface = Color(0xFF1E1E1E),
    onSurface = Color(0xFFB3C4CC),
    error = Color(0xFFCF6679),
    onError = Color.Black
)

internal val LocalColors = staticCompositionLocalOf { lightColors() }