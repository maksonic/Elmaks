package ru.maksonic.elmaks.feature.details.widget

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.maksonic.elmaks.core.ui.theme.ElmaksTheme
import ru.maksonic.elmaks.feature.details.update.DetailsViewModel
import ru.maksonic.elmaks.shared.R.*

/**
 * @author maksonic on 27.04.2022
 */
@Composable
internal fun DetailTopAppBar(titleDetailCity: String, viewModel: DetailsViewModel) {
    TopAppBar(
        title = {
            Text(
                text = titleDetailCity,
                style = ElmaksTheme.typography.toolbarTitle,
                color = ElmaksTheme.color.onBackground
            )
        },
        navigationIcon = {
            IconButton(onClick = { viewModel.backPressed() }) {
                Icon(
                    painter = painterResource(id = drawable.ic_round_arrow_back_24),
                    tint = ElmaksTheme.color.controlNormal,
                    contentDescription = stringResource(id = string.cd_scr_details_to_main_btn)
                )
            }
        },
        backgroundColor = ElmaksTheme.color.background,
        elevation = ElmaksTheme.elevation.elevationDisable
    )
}