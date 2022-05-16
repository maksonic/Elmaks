package ru.maksonic.elmaks.core.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @author maksonic on 01.05.2022
 */
val LocalElmaksShape = staticCompositionLocalOf<ElmaksShape> {
    error("No shapes provided")
}

data class ElmaksShape(
    val cornerNone: Shape,
    val cornerSmall: Shape,
    val cornerNormal: Shape,
    val cornerBig: Shape,
    val cornerExtra: Shape,
    val dp1BorderWidth: Dp,
    val dp2BorderWidth: Dp,
    val emojiBig: TextUnit
)

val shapes = ElmaksShape(
    cornerNone = RoundedCornerShape(0.dp),
    cornerSmall = RoundedCornerShape(4.dp),
    cornerNormal = RoundedCornerShape(8.dp),
    cornerBig = RoundedCornerShape(16.dp),
    cornerExtra = RoundedCornerShape(24.dp),
    dp1BorderWidth = 1.dp,
    dp2BorderWidth = 2.dp,
    emojiBig = 56.sp
)