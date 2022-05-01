package ru.maksonic.elmaks.core.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @author makosnic on 01.05.2022
 */
val LocalElmaksPadding = staticCompositionLocalOf<ElmaksPadding> {
    error("No paddings provided")
}

data class ElmaksPadding(
    val dp4: Dp,
    val dp8: Dp,
    val dp16: Dp,
    val dp24: Dp,
    val dp32: Dp,
    val dp54: Dp,
    val dp64: Dp,
    val dp72: Dp,
)

val paddings = ElmaksPadding(
    dp4 = 4.dp,
    dp8 = 8.dp,
    dp16 = 16.dp,
    dp24 = 24.dp,
    dp32 = 32.dp,
    dp54 = 54.dp,
    dp64 = 64.dp,
    dp72 = 72.dp
)