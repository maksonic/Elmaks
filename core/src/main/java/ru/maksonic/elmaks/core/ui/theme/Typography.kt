package ru.maksonic.elmaks.core.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * @author makosnic on 01.05.2022
 */
val LocalElmaksTypography = staticCompositionLocalOf<ElmaksTypography> {
    error("No font provided")
}

data class ElmaksTypography(
    val body: TextStyle,
    val title: TextStyle,
    val header: TextStyle,
    val toolbarTitle: TextStyle,
    val display: TextStyle,
    val caption: TextStyle
)

val typography = ElmaksTypography(
    body = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),
    title = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium
    ),
    header = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    ),
    toolbarTitle = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium
    ),
    display = TextStyle(
        fontSize = 34.sp,
        fontWeight = FontWeight.Bold
    ),
    caption = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
    )
)
