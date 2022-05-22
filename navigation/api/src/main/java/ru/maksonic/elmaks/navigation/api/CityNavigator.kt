package ru.maksonic.elmaks.navigation.api

import ru.maksonic.elmaks.core.elm.ElmNavigator
import javax.inject.Inject

/**
 * @Author maksonic on 18.05.2022
 */
class CityNavigator @Inject constructor() : ElmNavigator(), Router {

    override fun navigateToDetails(cityId: Long) {
        navigate(NavDestination.Details.route.plus(NavDestination.Details.id(cityId)))
    }
}