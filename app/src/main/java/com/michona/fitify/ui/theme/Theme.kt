package com.michona.fitify.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = Blue800,
    secondary = Blue700,
    onPrimary = Color.White,
    surface = Blue800,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.White,
)

@Composable
fun FitifyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes
    ) {
        CompositionLocalProvider(LocalRippleTheme provides SecondaryRippleTheme) {
            content()
        }
    }
}

@Immutable
private object SecondaryRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = RippleTheme.defaultRippleColor(
        contentColor = Blue700,
        lightTheme = MaterialTheme.colors.isLight
    )

    // TODO: Increase pressed alpha?
    @Composable
    override fun rippleAlpha() = RippleTheme.defaultRippleAlpha(
        contentColor = Color.White,
        lightTheme = MaterialTheme.colors.isLight
    )
}