package ru.maksonic.elmaks.feature.main.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.maksonic.elmaks.core.ui.theme.ElmaksTheme
import ru.maksonic.elmaks.shared.CityUi

/**
 * @author maksonic on 27.04.2022
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun CityItem(selectCity: () -> Unit, city: CityUi, modifier: Modifier = Modifier) {
    val dp8 = ElmaksTheme.padding.dp8
    val dp16 = ElmaksTheme.padding.dp16
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = dp8, bottom = dp8, start = dp16, end = dp16),
        shape = RoundedCornerShape(dp8),
        elevation = dp8,
        backgroundColor = ElmaksTheme.color.surface,
        onClick = selectCity
    ) {
        Row(
            modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = city.name,
                modifier = modifier.padding(top = dp16, bottom = dp16, start = dp16),
                style = TextStyle(color = ElmaksTheme.color.onSurface, fontSize = 20.sp)
            )
            Spacer(modifier.weight(1f))

            Text(
                text = city.postalCode.toString(),
                modifier = modifier.padding(end = dp16),
                style = TextStyle(color = ElmaksTheme.color.onSurface, fontSize = 20.sp)
            )
        }
    }
}