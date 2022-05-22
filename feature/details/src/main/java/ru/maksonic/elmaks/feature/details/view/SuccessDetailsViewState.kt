package ru.maksonic.elmaks.feature.details.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.maksonic.elmaks.core.ui.theme.ElmaksTheme
import ru.maksonic.elmaks.feature.details.R
import ru.maksonic.elmaks.feature.details.model.Model
import ru.maksonic.elmaks.feature.details.model.Msg
import ru.maksonic.elmaks.feature.details.widget.CityCard
import ru.maksonic.elmaks.feature.details.widget.CityDetailItem
import ru.maksonic.elmaks.shared.R.*

/**
 * @Author maksonic on 21.05.2022
 */
@Composable
internal fun SuccessDetailsViewState(
    model: Model,
    sendMsg: Message,
    modifier: Modifier = Modifier
) {
    val padding = ElmaksTheme.padding.dp16

    Column(modifier.padding(start = padding, end = padding)) {

        CityCard(model)

        Row(verticalAlignment = Alignment.CenterVertically) {
            CityDetailItem(title = "КЛАДР: ", body = model.city.kladrId.toString())

            Spacer(modifier.weight(1f))

            IconButton(
                onClick = { sendMsg(Msg.Ui.ShowKladrInfoDialog) },
                modifier.padding(top = ElmaksTheme.padding.dp8)
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_outline_info_24),
                    tint = ElmaksTheme.color.primary,
                    contentDescription = stringResource(id = string.cd_scr_details_kladr_info)
                )
            }
        }

        CityDetailItem(
            title = stringResource(id = R.string.item_title_federal_district),
            body = model.city.federalDistrict
        )
        CityDetailItem(
            title = stringResource(id = R.string.item_title_region),
            body = model.city.region
        )
        CityDetailItem(
            title = stringResource(id = R.string.item_title_postal_code),
            body = model.city.postalCode.toString()
        )
        CityDetailItem(
            title = stringResource(id = R.string.item_title_timezone),
            body = model.city.timezone
        )
        CityDetailItem(
            title = stringResource(id = R.string.item_title_population),
            body = model.city.population
        )
        CityDetailItem(
            title = stringResource(id = R.string.item_title_foundation_year),
            body = model.city.foundationYear.toString()
        )
    }
}

