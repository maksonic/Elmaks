package ru.maksonic.elmaks.feature.main.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import ru.maksonic.elmaks.core.ui.theme.ElmaksTheme
import ru.maksonic.elmaks.feature.main.R

/**
 * @Author maksonic on 18.05.2022
 */
@Composable
internal fun EmptySearchResultViewState(modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "\uD83D\uDE22", fontSize = 56.sp)
        Text(
            text = stringResource(id = R.string.hint_empty_search_list),
            style = ElmaksTheme.typography.title,
            color = ElmaksTheme.color.primaryText
        )
    }
}
