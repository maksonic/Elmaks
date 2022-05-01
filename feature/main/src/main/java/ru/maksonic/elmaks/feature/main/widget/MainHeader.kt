package ru.maksonic.elmaks.feature.main.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.maksonic.elmaks.shared.R.*

/**
 * @author makosnic on 27.04.2022
 */
@Composable
fun MainHeader(
    modifier: Modifier = Modifier,
    searchable: MutableState<String>
) {
    if (searchable.value.isEmpty()) {
        Text(
            text = stringResource(id = string.scr_main_header_title),
            style = TextStyle(
                color = MaterialTheme.colors.primary,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold
            ),
            overflow = TextOverflow.Ellipsis,
            modifier = modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
        )
    } else {
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colors.onBackground,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Normal,
                    )
                ) {
                    append(stringResource(id = string.scr_main_header_search_action))
                }
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colors.primary,
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold,

                        )
                ) {
                    append(searchable.value)
                }
            },
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
        )
    }
}