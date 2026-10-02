package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = WasabiGreen,
    onPrimary = SurfaceWhite,
    primaryContainer = WasabiDark,
    onPrimaryContainer = SurfaceWhite,
    secondary = SkyBlue,
    onSecondary = SurfaceWhite,
    secondaryContainer = SkyBlueDark,
    tertiary = TamagoYellow,
    background = Color(0xFF131F24),
    surface = Color(0xFF1A2E3B),
    onBackground = SurfaceWhite,
    onSurface = SurfaceWhite
)

private val LightColorScheme = lightColorScheme(
    primary = WasabiGreen,
    onPrimary = SurfaceWhite,
    primaryContainer = WasabiLight,
    onPrimaryContainer = WasabiDark,
    secondary = SkyBlue,
    onSecondary = SurfaceWhite,
    secondaryContainer = SkyBlueLight,
    tertiary = TamagoYellow,
    background = RiceCream,
    surface = SurfaceWhite,
    onBackground = NoriDark,
    onSurface = NoriDark,
    outline = CardBorder
)

@Composable
fun SushiLanguageTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
