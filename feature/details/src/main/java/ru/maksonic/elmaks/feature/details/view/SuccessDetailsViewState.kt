package ru.maksonic.elmaks.feature.details.view

import android.media.CamcorderProfile.get
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.maksonic.elmaks.core.ui.theme.ElmaksTheme
import ru.maksonic.elmaks.feature.details.R
import ru.maksonic.elmaks.feature.details.model.Model
import ru.maksonic.elmaks.feature.details.model.Msg
import ru.maksonic.elmaks.feature.details.widget.CityDetailItem
import kotlin.random.Random

/**
 * @Author maksonic on 21.05.2022
 */

@Composable
internal fun SuccessDetailsViewState(
    model: Model,
    sendMsg: Message,
    modifier: Modifier = Modifier
) {

    Column() {
        val cardPadding = ElmaksTheme.padding.dp16

        Card(
            elevation = ElmaksTheme.elevation.dp8,
            shape = ElmaksTheme.shape.cornerBig,
            backgroundColor = model.cardBgColor.value,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = cardPadding, end = cardPadding, top = cardPadding)
        ) {
            Column(
                modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = model.city.name,
                    style = ElmaksTheme.typography.display,
                    color = ElmaksTheme.color.primaryText,
                    modifier = modifier.padding(top = ElmaksTheme.padding.dp8)
                )

                Spacer(modifier = Modifier.height(ElmaksTheme.padding.dp32))

                Box(
                    modifier
                        .fillMaxWidth()
                        .wrapContentHeight(), contentAlignment = Alignment.BottomCenter
                ) {
                    Image(
                        painter = painterResource(ElmaksTheme.image.onCityCardImage),
                        contentDescription = "",
                        modifier = modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.1f)
                    )
                }
            }
        }

        Column(modifier.padding(start = cardPadding, end = cardPadding, top = cardPadding)) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                CityDetailItem(
                    title = "КЛАДР: ",
                    body = model.city.kladrId.toString()
                )
                Spacer(modifier.weight(1f))
                IconButton(
                    onClick = { sendMsg(Msg.Ui.ShowKladrInfoDialog) },
                    modifier.padding(top = ElmaksTheme.padding.dp8)
                ) {
                    Icon(
                        painterResource(id = R.drawable.ic_outline_info_24),
                        tint = ElmaksTheme.color.primary,
                        contentDescription = stringResource(
                            id = ru.maksonic.elmaks.shared.R.string.cd_scr_details_kladr_info
                        )
                    )
                }
            }

            CityDetailItem(
                title = "Федеральный округ: ",
                body = model.city.federalDistrict
            )

            CityDetailItem(
                title = "Регион: ",
                body = model.city.region
            )

            CityDetailItem(
                title = "Почтовый индекс: ",
                body = model.city.postalCode.toString()
            )
            CityDetailItem(
                title = "Часовой пояс: ",
                body = model.city.timezone
            )
            CityDetailItem(
                title = "Население: ",
                body = model.city.population
            )
            CityDetailItem(
                title = "Год основания: ",
                body = model.city.foundationYear.toString()
            )
        }
    }
}

