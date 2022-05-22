package ru.maksonic.elmaks.feature.details.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import ru.maksonic.elmaks.core.elm.Message
import ru.maksonic.elmaks.shared.CityUi

/**
 * @Author maksonic on 21.05.2022
 */
sealed class Msg : Message {

    @Immutable
    sealed class Ui : Msg() {
        data class RetryFetchCityDetails(val cityId: Long) : Ui()
        object ShowKladrInfoDialog : Internal()

    }

    @Immutable
    sealed class Internal : Msg() {
        data class FetchCityDetails(val cityId: Long) : Internal()
        data class CityDetailsSuccess(val city: CityUi) : Internal()
        data class Error(val message: String) : Internal()
        data class GenerateCardColor(val isDarkMode: State<Boolean>): Internal()
        data class ApplyCityCardColor(val color: MutableState<Color>): Internal()
    }
}