package ru.maksonic.elmaks.feature.main.widget.topbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.maksonic.elmaks.core.ui.theme.ElmaksTheme
import ru.maksonic.elmaks.core.ui.widget.IconActionButton
import ru.maksonic.elmaks.feature.main.view.Message
import ru.maksonic.elmaks.feature.main.widget.SortingDropdownMenu
import ru.maksonic.elmaks.shared.R.drawable
import ru.maksonic.elmaks.shared.R.string

/**
 * @author maksonic on 01.05.2022
 */
@Composable
internal fun ButtonSortedList(filterBtnVisibility: Boolean, sendMsg: Message) {
    val popupThemeMenu = remember { mutableStateOf(false) }

    AnimatedVisibility(visible = filterBtnVisibility) {
        IconActionButton(onClick = { popupThemeMenu.value = true }) {
            Icon(
                painter = painterResource(id = drawable.ic_round_filter_list_24),
                tint = ElmaksTheme.color.controlNormal,
                contentDescription = stringResource(
                    id = string.cd_scr_main_sorted_btn
                )
            )
        }
        SortingDropdownMenu(popupThemeMenu = popupThemeMenu, sendMsg)
    }
}
