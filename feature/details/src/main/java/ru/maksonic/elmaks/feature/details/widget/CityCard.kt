package ru.maksonic.elmaks.feature.details.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import ru.maksonic.elmaks.core.ui.theme.ElmaksTheme
import ru.maksonic.elmaks.feature.details.model.Model

/**
 * @Author maksonic on 22.05.2022
 */
@Composable
fun CityCard(model: Model, modifier: Modifier = Modifier) {
    val cardPadding = ElmaksTheme.padding.dp8

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
                modifier = modifier.padding(top = cardPadding)
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
}