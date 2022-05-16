package ru.maksonic.elmaks.navigation

/**
 * @author maksonic on 01.05.2022
 */
sealed class Route(val route: String)

object NavDestination : Route("graph") {
    internal object Main : Route("main")
    internal object Details : Route("detail")
}