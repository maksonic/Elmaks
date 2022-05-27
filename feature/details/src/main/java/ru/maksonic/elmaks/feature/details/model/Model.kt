package ru.maksonic.elmaks.feature.details.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import ru.maksonic.elmaks.core.elm.StateModel
import ru.maksonic.elmaks.shared.CityUi

/**
 * @Author maksonic on 21.05.2022
 */
@Immutable
data class Model(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val city: CityUi = CityUi(
        kladrId = 0,
        name = "",
        postalCode = 0,
        population = "",
        foundationYear = 0,
        region = "",
        federalDistrict = "",
        timezone = ""
    ),
    val isError: Boolean = false,
    val errorMessage: String = "",
    val cardBgColor: MutableState<Color> = mutableStateOf(Color(0xFFD6EAF8)),
    val isShowKladrDialog: MutableState<Boolean> = mutableStateOf(false)
) : StateModel