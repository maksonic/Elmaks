package ru.maksonic.elmaks.feature.main.program

import ru.maksonic.elmaks.core.ElmProgram
import ru.maksonic.elmaks.feature.main.model.Cmd
import ru.maksonic.elmaks.feature.main.model.Msg
import ru.maksonic.elmaks.navigation.api.Navigator

/**
 * @Author maksonic on 18.05.2022
 */
interface NavigationProgram : ElmProgram<Msg, Cmd> {

    fun navigateToDetailsScreen(cityId: Long)

    class Base(private val navigator: Navigator) : NavigationProgram {
        override suspend fun execute(cmd: Cmd, consumer: (Msg) -> Unit) {
            when (cmd) {
                is Cmd.Navigation.ToCity -> navigateToDetailsScreen(cmd.cityId)
                else -> {}
            }
        }

        override fun navigateToDetailsScreen(cityId: Long) = navigator.navigateToDetails(cityId)
    }
}