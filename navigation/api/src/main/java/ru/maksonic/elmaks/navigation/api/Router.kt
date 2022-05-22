package ru.maksonic.elmaks.navigation.api

import ru.maksonic.elmaks.shared.CityUi

/**
 * @Author maksonic on 18.05.2022
 */
interface Router {
    fun navigateToDetails(cityId: Long)
}