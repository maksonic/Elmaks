package ru.maksonic.elmaks.feature.details.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import ru.maksonic.elmaks.core.ui.theme.ElmaksTheme

/**
 * @Author maksonic on 21.05.2022
 */
@Composable
internal fun CityDetailItem(title: String, body: String, modifier: Modifier = Modifier) {
    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = ElmaksTheme.color.primary,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            ) {
                append(title)
            }
            withStyle(
                style = SpanStyle(
                    color = ElmaksTheme.color.primaryText,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,

                    )
            ) {
                append(body)
            }
        },
        modifier = modifier.padding(top = ElmaksTheme.padding.dp8)
    )
}