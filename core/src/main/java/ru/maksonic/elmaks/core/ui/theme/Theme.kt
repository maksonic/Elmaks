package ru.maksonic.elmaks.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.maksonic.elmaks.core.R

/**
 * @author maksonic on 01.05.2022
 */
@Composable
fun ElmaksTheme(
    lightPalette: ElmaksColor = baseLightPalette,
    darkPalette: ElmaksColor = baseDarkPalette,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) darkPalette else lightPalette
    val buttons = ElmaksButton(
        themeSelector = if (darkTheme) R.drawable.ic_round_light_mode_24
        else R.drawable.ic_round_mode_night_24
    )
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(colors.background)
    }

    CompositionLocalProvider(
        LocalElmaksButton provides buttons,
        LocalElmaksColors provides colors,
        LocalElmaksComponentSize provides componentSize,
        LocalElmaksElevation provides elevations,
        LocalElmaksPadding provides paddings,
        LocalElmaksShape provides shapes,
        LocalElmaksTypography provides typography,
        content = content
    )
}

object ElmaksTheme {
    val button: ElmaksButton @Composable get() = LocalElmaksButton.current
    val color: ElmaksColor @Composable get() = LocalElmaksColors.current
    val componentSize: ElmaksComponentSize @Composable get() = LocalElmaksComponentSize.current
    val elevation: ElmaksElevation @Composable get() = LocalElmaksElevation.current
    val padding: ElmaksPadding @Composable get() = LocalElmaksPadding.current
    val shape: ElmaksShape @Composable get() = LocalElmaksShape.current
    val typography: ElmaksTypography @Composable get() = LocalElmaksTypography.current
}