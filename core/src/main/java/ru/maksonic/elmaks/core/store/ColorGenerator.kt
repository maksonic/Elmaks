package ru.maksonic.elmaks.core.store

import androidx.compose.ui.graphics.Color
import javax.inject.Inject

/**
 * @Author maksonic on 21.05.2022
 */
interface ColorGenerator {
    fun randomColor(isDarkMode: Boolean): Color

    class Generation @Inject constructor() : ColorGenerator {
        private val lightColors = arrayOf(
            0xFFE6E6FA, 0xFFB0C4DE, 0xFFF0FFF0, 0xFFFFE4E1, 0xFFFFDAB9, 0xFFA2D9CE, 0xFFFFC0CB,
            0xFF87CEFA, 0xFFAFEEEE, 0xFFE6B0AA, 0xFFABEBC6, 0xFFF9E79F, 0xFFD7BDE2, 0xFFD6EAF8,
            0xFFD4EFDF, 0xFFDCDCDC, 0xFFFFA07A, 0xFFEEE8AA, 0xFF90EE90, 0xFFE0FFFF,
        )
        private val darkColors = arrayOf(
            0xFF702727, 0xFF5E4916, 0xFF2C471B, 0xFF1E463D, 0xFF134155, 0xFF293261, 0xFF592961,
            0xFF61294A, 0xFF3E3E3E, 0xFF591E1E, 0xFF641051, 0xFF233A5C, 0xFF215126, 0xFF005365,
            0xFF653000, 0xFF641833, 0xFF16453A, 0xFF20236A, 0xFF093762, 0xFF41216A,
        )

        override fun randomColor(isDarkMode: Boolean): Color {
            val colors = if (isDarkMode) darkColors else lightColors
            return Color(colors.random())
        }
    }
}