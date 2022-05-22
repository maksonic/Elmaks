package ru.maksonic.elmaks.feature.main.program

import ru.maksonic.elmaks.core.elm.ElmProgram
import ru.maksonic.elmaks.feature.main.model.Cmd
import ru.maksonic.elmaks.feature.main.model.Msg
import ru.maksonic.elmaks.navigation.api.CityNavigator
import javax.inject.Inject

/**
 * @Author maksonic on 18.05.2022
 */
class NavigationProgram @Inject constructor(
    private val navigator: CityNavigator
) : ElmProgram<Msg, Cmd> {

    override suspend fun execute(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.Navigation.ToCity -> navigator.navigateToDetails(cmd.cityId)
            else -> {}
        }
    }
}