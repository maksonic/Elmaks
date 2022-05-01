package ru.maksonic.elmaks.feature.main.widget.topbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.maksonic.elmaks.shared.R.drawable
import ru.maksonic.elmaks.shared.R.string

/**
 * @author maksonic on 01.05.2022
 */
@Composable
fun MainAppBarNavIcon(modifier: Modifier = Modifier, visible: Boolean, cancelSearch: () -> Unit) {

    if (visible) {
        Icon(
            painter = painterResource(id = drawable.ic_round_insert_emoticon_24),
            modifier = modifier.padding(12.dp),
            tint = MaterialTheme.colors.onBackground,
            contentDescription = null
        )
    } else {
        IconButton(onClick = cancelSearch) {
            Icon(
                painter = painterResource(id = drawable.ic_round_arrow_back_24),
                tint = MaterialTheme.colors.onBackground,
                contentDescription = stringResource(id = string.cd_scr_main_back_from_search_btn)
            )
        }
    }
}