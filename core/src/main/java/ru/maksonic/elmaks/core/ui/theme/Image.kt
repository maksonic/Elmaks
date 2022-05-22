package ru.maksonic.elmaks.core.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf

/**
 * @Author maksonic on 22.05.2022
 */
val LocalElmaksImage = staticCompositionLocalOf<ElmaksImage> {
    error("No image provided")
}

data class ElmaksImage(val onCityCardImage: Int)