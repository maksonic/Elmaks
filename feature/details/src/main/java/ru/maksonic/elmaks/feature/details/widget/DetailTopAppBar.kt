package ru.maksonic.elmaks.feature.details.widget

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.maksonic.elmaks.shared.R.*

/**
 * @author maksonic on 27.04.2022
 */
@Composable
fun DetailTopAppBar(titleDetailCity: String, backPressed: () -> Unit) {
    TopAppBar(
        title = { Text(text = titleDetailCity) },
        navigationIcon = {
            IconButton(onClick = backPressed) {
                Icon(
                    painter = painterResource(id = drawable.ic_round_arrow_back_24),
                    tint = MaterialTheme.colors.onBackground,
                    contentDescription = stringResource(id = string.cd_scr_details_to_main_btn)
                )
            }
        },
        backgroundColor = MaterialTheme.colors.background,
        elevation = 1.dp
    )
}