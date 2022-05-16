package ru.maksonic.elmaks.core.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf

/**
 * @author maksonic on 01.05.2022
 */
val LocalElmaksButton = staticCompositionLocalOf<ElmaksButton> {
    error("No button provided")
}

data class ElmaksButton(
    val themeSelector: Int
)