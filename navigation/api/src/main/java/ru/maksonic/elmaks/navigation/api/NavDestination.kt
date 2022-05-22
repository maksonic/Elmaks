package ru.maksonic.elmaks.navigation.api

/**
 * @author maksonic on 01.05.2022
 */
sealed class Route(val route: String)

object NavDestination: Route("root") {
    object Main : Route("main")
    object Details : Route("details/{cityId}") {
        fun id(cityId: Long) = "/${cityId}"
    }
}