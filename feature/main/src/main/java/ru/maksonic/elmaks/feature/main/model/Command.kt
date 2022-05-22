package ru.maksonic.elmaks.feature.main.model

import ru.maksonic.elmaks.core.elm.Command

/**
 * @Author maksonic on 16.05.2022
 */
sealed class Cmd : Command {
    object FetchCities : Cmd()
    object RefreshCites : Cmd()
    data class SwitchTheme(val isDarkMode: Boolean) : Cmd()

    sealed class Navigation : Cmd() {
        data class ToCity(val cityId: Long) : Navigation()
    }
}