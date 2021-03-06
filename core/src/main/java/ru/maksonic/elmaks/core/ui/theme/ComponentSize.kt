package ru.maksonic.elmaks.core.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @author maksonic on 01.05.2022
 */
val LocalElmaksComponentSize = staticCompositionLocalOf<ElmaksComponentSize> {
    error("No component size provided")
}

data class ElmaksComponentSize(
    val btnNav: Dp,
    val btnPrimaryHeight: Dp,
    val circularProgressIndicatorStrokeWidth: Dp,
    val enteredMaxLength: Int
)

val componentSize = ElmaksComponentSize(
    btnNav = 24.dp,
    btnPrimaryHeight = 54.dp,
    circularProgressIndicatorStrokeWidth = 5.dp,
    enteredMaxLength = 50
)