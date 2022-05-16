package ru.maksonic.elmaks.core.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @author maksonic on 01.05.2022
 */
val LocalElmaksElevation = staticCompositionLocalOf<ElmaksElevation> {
    error("No elevation provided")
}

data class ElmaksElevation(
    val elevationDisable: Dp,
    val dp1: Dp,
    val dp2: Dp,
    val dp4: Dp,
    val dp8: Dp,
    val dp16: Dp,
)

val elevations = ElmaksElevation(
    elevationDisable = 0.dp,
    dp1 = 1.dp,
    dp2 = 2.dp,
    dp4 = 4.dp,
    dp8 = 8.dp,
    dp16 = 16.dp,
)