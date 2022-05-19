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
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        backgroundColor = ElmaksTheme.color.surface,
        onClick = selectCity
    ) {
        Row(
            modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = city.name,
                modifier = modifier.padding(top = 16.dp, bottom = 16.dp, start = 16.dp),
                style = TextStyle(color = ElmaksTheme.color.onSurface, fontSize = 20.sp)
            )
            Spacer(modifier.weight(1f))

            Text(
                text = city.postalCode.toString(),
                modifier = modifier.padding(end = 16.dp),
                style = TextStyle(color = ElmaksTheme.color.onSurface, fontSize = 20.sp)
            )
        }
    }
}