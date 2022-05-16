package ru.maksonic.elmaks.feature.main.model

import ru.maksonic.elmaks.core.StateModel
import ru.maksonic.elmaks.shared.CityUi

/**
 * @Author maksonic on 16.05.2022
 */
data class Model(
    val isLoading: Boolean = false,
    val isSuccessLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val isErrorLoading: Boolean = false,
    val errorFetchingMsg: String = "",
    val cityList: List<CityUi> = emptyList(),
    val filteredList: MutableList<CityUi> = mutableListOf(),
    val inputSearchCity: String = ""
) : StateModel