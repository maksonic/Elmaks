package ru.maksonic.elmaks.feature.details.program

import androidx.compose.runtime.mutableStateOf
import ru.maksonic.elmaks.core.elm.ElmProgram
import ru.maksonic.elmaks.core.store.ColorGenerator
import ru.maksonic.elmaks.feature.details.model.Cmd
import ru.maksonic.elmaks.feature.details.model.Msg
import javax.inject.Inject

/**
 * @Author maksonic on 22.05.2022
 */
class GenerateCityCardProgram @Inject constructor(
    private val colorGenerator: ColorGenerator
): ElmProgram<Msg, Cmd> {
    override suspend fun execute(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.CardColorBackgroundGeneration -> generateCardBackground(consumer, cmd)
            else -> {}
        }
    }

    private fun generateCardBackground(
        consumer: (Msg) -> Unit,
        cmd: Cmd.CardColorBackgroundGeneration
    ) {
        val color = colorGenerator.randomColor(cmd.isDarkMode.value)
        consumer(Msg.Internal.ApplyCityCardColor(mutableStateOf(color)))
    }
}