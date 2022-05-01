package ru.maksonic.elmaks.core.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * @author makosnic on 01.05.2022
 */
val LocalElmaksColors = staticCompositionLocalOf<ElmaksColor> {
    error("No colors provided")
}
data class ElmaksColor(
    val primary: Color,
    val primaryVariant: Color,
    val onPrimary: Color,
    val secondary: Color,
    val secondaryVariant: Color,
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val divider: Color,
    val error: Color,
    val onError: Color,
    val transparent: Color,
    val shadow: Color,
    val primaryText: Color,
    val secondaryText: Color,
    val tertiaryText: Color,
)