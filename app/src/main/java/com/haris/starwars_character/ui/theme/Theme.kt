package com.haris.starwars_character.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = StarWarsYellow,
    onPrimary = onPrimary,
    secondary = StarWarsGold,
    tertiary = StarWarsOrange,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    outline = outlineDark,
)

private val LightColorScheme = lightColorScheme(
    primary = StarWarsYellow,
    onPrimary = onPrimary,
    secondary = StarWarsGold,
    tertiary = StarWarsOrange,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    outline = outlineLight,
)

@Composable
fun Starwars_characterTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}