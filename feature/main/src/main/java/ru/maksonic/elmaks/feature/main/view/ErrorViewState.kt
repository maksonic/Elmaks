package ru.maksonic.elmaks.feature.main.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.maksonic.elmaks.core.ui.theme.ElmaksTheme
import ru.maksonic.elmaks.shared.R
import ru.maksonic.elmaks.feature.main.model.Model
import ru.maksonic.elmaks.feature.main.model.Msg

/**
 * @author maksonic on 12.05.2022
 */
@Composable
internal fun ErrorViewState(model: Model, sendMsg: Message, modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxSize()
            .background(ElmaksTheme.color.background),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Row(Modifier.fillMaxWidth(0.8f)) {
                Image(
                    painter = painterResource(id = R.drawable.error_placeholder),
                    contentDescription = "",
                    modifier = modifier.aspectRatio(1f)
                )
            }
            Text(
                text = model.errorFetchingMsg,
                style = ElmaksTheme.typography.caption,
                color = ElmaksTheme.color.secondaryText
            )

            Spacer(modifier.height(ElmaksTheme.padding.dp32))

            Button(
                onClick = { sendMsg(Msg.Ui.FetchCityList) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ElmaksTheme.color.primary,
                    contentColor = ElmaksTheme.color.onPrimary
                )
            ) {
                Text(
                    stringResource(id = ru.maksonic.elmaks.shared.R.string.hint_retry_loading),
                    style = ElmaksTheme.typography.body
                )
            }
        }
    }
}