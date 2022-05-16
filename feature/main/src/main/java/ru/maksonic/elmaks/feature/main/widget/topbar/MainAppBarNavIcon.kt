package ru.maksonic.elmaks.feature.main.widget.topbar

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.maksonic.elmaks.core.ui.theme.ElmaksTheme
import ru.maksonic.elmaks.core.ui.widget.IconActionButton
import ru.maksonic.elmaks.feature.main.model.MainFeature
import ru.maksonic.elmaks.feature.main.update.MainViewModel
import ru.maksonic.elmaks.feature.main.view.Message
import ru.maksonic.elmaks.shared.R.drawable
import ru.maksonic.elmaks.shared.R.string

/**
 * @author maksonic on 01.05.2022
 */
@Composable
internal fun MainAppBarNavIcon(
    msg: Message,
    modifier: Modifier = Modifier,
    visible: Boolean, cancelSearch: () -> Unit,
    viewModel: MainViewModel,
    isDarkMode: State<Boolean>,
) {
    if (visible) {
        IconActionButton(onClick = {
            msg(MainFeature.Msg.OnSwitchTheme(!isDarkMode.value))
       //     viewModel.setTheme(isDark = !isDarkMode.value)
        }) {
            Icon(
                painter = painterResource(id = ElmaksTheme.button.themeSelector),
                tint = ElmaksTheme.color.controlNormal,
                contentDescription = stringResource(id = string.cd_scr_main_switch_theme_btn)
            )
        }
    } else {
        IconActionButton(onClick = cancelSearch) {
            Icon(
                painter = painterResource(id = drawable.ic_round_arrow_back_24),
                tint = ElmaksTheme.color.controlNormal,
                contentDescription = stringResource(id = string.cd_scr_main_back_from_search_btn)
            )
        }
    }
}