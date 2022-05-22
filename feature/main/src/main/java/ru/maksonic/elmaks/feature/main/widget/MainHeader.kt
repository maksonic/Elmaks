package ru.maksonic.elmaks.feature.main.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import ru.maksonic.elmaks.core.ui.theme.ElmaksTheme
import ru.maksonic.elmaks.feature.main.model.Model
import ru.maksonic.elmaks.shared.R.string

/**
 * @author maksonic on 27.04.2022
 */
@Composable
internal fun MainHeader(
    model: Model,
    modifier: Modifier = Modifier,
) {
    val dp16 = ElmaksTheme.padding.dp16
    if (model.inputSearchCity.value.text.isEmpty()) {
        Text(
            text = stringResource(id = string.scr_main_header_title),
            style = ElmaksTheme.typography.display,
            color = ElmaksTheme.color.primary,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier.padding(start = dp16, top = dp16, bottom = dp16)
        )
    } else {
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = ElmaksTheme.color.secondaryText,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Normal,
                    )
                ) {
                    append(stringResource(id = string.scr_main_header_search_action))
                }
                withStyle(
                    style = SpanStyle(
                        color = ElmaksTheme.color.primary,
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold,

                        )
                ) {
                    append(model.inputSearchCity.value.text)
                }
            },
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier.padding(
                top = dp16, bottom = ElmaksTheme.padding.dp8, start = dp16, end = dp16)
        )
    }
}