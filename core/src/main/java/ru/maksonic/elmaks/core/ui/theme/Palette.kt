package ru.maksonic.elmaks.core.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * @author maksonic on 01.05.2022
 */
val Black = Color(0xFF000000)
val CarmineRed = Color(0xFFB00020)
val DimGray = Color(0xFFEDEDED)
val GainsboroGray = Color(0xFFDCDCDC)
val Nero = Color(0xFF212121)
val ShadowDark = Color(0x52000000)
val Solitude = Color(0xFFF7F7F7)
val ShuttleGray = Color(0xFF65676B)
val Transparent = Color(0x00000000)
val White = Color(0xFFFFFFFF)
val AzureRadiance = Color(0xFF0076FF)
val JordyBlue = Color(0xFF7CB8F0)

val baseLightPalette = ElmaksColor(
    primary = AzureRadiance,
    primaryVariant = GainsboroGray,
    onPrimary = White,
    secondary = ShuttleGray,
    secondaryVariant = DimGray,
    background = Solitude,
    onBackground = White,
    surface = White,
    onSurface = Nero,
    divider = GainsboroGray,
    error = CarmineRed,
    onError = White,
    transparent = Transparent,
    shadow = ShadowDark,
    primaryText = Black,
    secondaryText = ShuttleGray,
    tertiaryText = DimGray,
    controlNormal = Nero
)

val baseDarkPalette = ElmaksColor(
    primary = JordyBlue,
    primaryVariant = Nero,
    onPrimary = Black,
    secondary = ShuttleGray,
    secondaryVariant = DimGray,
    background = Black,
    onBackground = White,
    surface = Nero,
    onSurface = Solitude,
    divider = ShadowDark,
    error = CarmineRed,
    onError = White,
    transparent = Transparent,
    shadow = ShadowDark,
    primaryText = White,
    secondaryText = Solitude,
    tertiaryText = DimGray,
    controlNormal = White
)
