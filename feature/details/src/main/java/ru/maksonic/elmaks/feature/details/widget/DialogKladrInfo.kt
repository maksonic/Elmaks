package ru.maksonic.elmaks.feature.details.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import ru.maksonic.elmaks.core.ui.theme.ElmaksTheme
import ru.maksonic.elmaks.feature.details.R

/**
 * @Author maksonic on 22.05.2022
 */
@Composable
fun DialogKladrInfo(
    state: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    content: @Composable (() -> Unit)? = null
) {
    val padding = ElmaksTheme.padding.dp16

    if (state.value) {
        AlertDialog(
            onDismissRequest = { state.value = false },
            title = { DialogTitle() },
            text = content,
            dismissButton = {},
            confirmButton = {
                Button(
                    onClick = { state.value = false },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = ElmaksTheme.color.primary,
                        contentColor = ElmaksTheme.color.onPrimary
                    ),
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(
                            start = padding,
                            end = padding,
                            bottom = padding
                        )
                ) {
                    Text(stringResource(id = R.string.title_btn_confirm_dialog))
                }
            },
            backgroundColor = ElmaksTheme.color.surface,
            modifier = modifier.clip(ElmaksTheme.shape.cornerExtra)
        )
    }
}

@Composable
private fun DialogTitle(modifier: Modifier = Modifier) {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.title_kladr_dialog),
            style = ElmaksTheme.typography.toolbarTitle,
            color = ElmaksTheme.color.primaryText,
            modifier = modifier.padding(bottom = ElmaksTheme.padding.dp16)
        )
        Divider(
            color = ElmaksTheme.color.divider,
            modifier = modifier.padding(bottom = ElmaksTheme.padding.dp8)
        )
        Text(
            text = stringResource(id = R.string.txt_body_what_is_kladr),
            color = ElmaksTheme.color.secondaryText,
            style = ElmaksTheme.typography.title,
            modifier = modifier.padding(bottom = ElmaksTheme.padding.dp8)
        )
    }
}