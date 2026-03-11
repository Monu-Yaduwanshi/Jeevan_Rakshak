package com.example.jeevan_rakshak.viewmodels

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Custom Colors
val ButtonRed = Color(0xFFD22030)      // For Sign In/Sign Up buttons
val FieldBackground = Color(0xFFFfff) // Text field background
val HeadingBlue = Color(0xFF719EE5)     // For headings
val TextBlue = Color(0xFF719EE5)        // For text field labels

// App Colors Object
object AppColors {
    val ButtonRed = Color(0xFFD22030)
    val FieldBackground = Color(0xFFFFDF91)
    val HeadingBlue = Color(0xFF719EE5)
    val TextBlue = Color(0xFF719EE5)
    val White = Color.White
    val Black = Color.Black
    val LightGray = Color(0xFFF5F5F5)
}

private val LightColors = lightColorScheme(
    primary = ButtonRed,
    secondary = HeadingBlue,
    background = FieldBackground,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black
)

private val DarkColors = darkColorScheme(
    primary = ButtonRed,
    secondary = HeadingBlue,
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun JeevanRakshakTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = MaterialTheme.typography,
        content = content
    )
}