package ru.maksonic.elmaks.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * @author makosnic on 01.05.2022
 */
@Composable
fun ElmaksTheme(
    lightPalette: ElmaksColor = baseLightPalette,
    darkPalette: ElmaksColor = baseDarkPalette,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val colors = if (darkTheme) darkPalette else lightPalette

    SideEffect {
        systemUiController.setSystemBarsColor(colors.background)
    }

    CompositionLocalProvider(
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
    val color: ElmaksColor @Composable get() = LocalElmaksColors.current
    val componentSize: ElmaksComponentSize @Composable get() = LocalElmaksComponentSize.current
    val elevation: ElmaksElevation @Composable get() = LocalElmaksElevation.current
    val shape: ElmaksShape @Composable get() = LocalElmaksShape.current
    val typography: ElmaksColor @Composable get() = LocalElmaksColors.current
}