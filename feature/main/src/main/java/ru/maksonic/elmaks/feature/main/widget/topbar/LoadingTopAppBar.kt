package ru.maksonic.elmaks.feature.main.widget.topbar

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.maksonic.elmaks.core.ui.theme.ElmaksTheme
import ru.maksonic.elmaks.feature.main.R

/**
 * @author maksonic on 12.05.2022
 */
@Composable
internal fun LoadingTopAppBar() {
    TopAppBar(
        backgroundColor = ElmaksTheme.color.background,
        elevation = ElmaksTheme.elevation.dp1,
        title = {
            Text(
                text = stringResource(id = R.string.toolbar_title_loading_state),
                style = ElmaksTheme.typography.toolbarTitle,
                color = ElmaksTheme.color.primaryText
            )
        }
    )
}