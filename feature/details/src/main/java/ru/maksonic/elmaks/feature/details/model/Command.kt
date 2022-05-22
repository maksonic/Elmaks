package ru.maksonic.elmaks.feature.details.model

import androidx.compose.runtime.State
import ru.maksonic.elmaks.core.elm.Command

/**
 * @Author maksonic on 21.05.2022
 */
sealed class Cmd : Command {
    data class FetchCityInfo(val cityId: Long) : Cmd()
    data class CardColorBackgroundGeneration(val isDarkMode: State<Boolean>) : Cmd()
}