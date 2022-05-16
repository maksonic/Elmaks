package ru.maksonic.elmaks.feature.main.program

import ru.maksonic.elmaks.core.ElmProgram
import ru.maksonic.elmaks.core.store.AppThemeSetting
import ru.maksonic.elmaks.feature.main.model.MainFeature
import javax.inject.Inject

/**
 * @author maksonic on 12.05.2022
 */
interface SwitchThemeProgram : ElmProgram<MainFeature.Msg, MainFeature.Cmd> {

    suspend fun switchTheme(isDarkMode: Boolean)

    class Base @Inject constructor(private val themeSetting: AppThemeSetting) : SwitchThemeProgram {

        override suspend fun execute(cmd: MainFeature.Cmd, consumer: (MainFeature.Msg) -> Unit) {
            when (cmd) {
                is MainFeature.Cmd.SwitchTheme -> switchTheme(cmd.isDarkMode)
                else -> {}
            }
        }

        override suspend fun switchTheme(isDarkMode: Boolean) {
            themeSetting.setTheme(isDarkMode)
        }
    }
}