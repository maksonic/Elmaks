package ru.maksonic.elmaks.feature.main.program

import ru.maksonic.elmaks.core.elm.ElmProgram
import ru.maksonic.elmaks.core.store.AppThemeSetting
import ru.maksonic.elmaks.feature.main.model.Cmd
import ru.maksonic.elmaks.feature.main.model.Msg
import javax.inject.Inject

/**
 * @author maksonic on 12.05.2022
 */
class SwitchThemeProgram @Inject constructor(
    private val themeSetting: AppThemeSetting
) : ElmProgram<Msg, Cmd> {
    override suspend fun execute(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.SwitchTheme -> themeSetting.setTheme(cmd.isDarkMode)
            else -> {}
        }
    }
}