package ru.maksonic.elmaks.feature.main.widget.topbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.maksonic.elmaks.shared.R.drawable
import ru.maksonic.elmaks.shared.R.string

/**
 * @author maksonic on 01.05.2022
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainTopAppBar(
    modifier: Modifier = Modifier,
    filterCityList: () -> Unit,
    searchable: MutableState<String>,

    ) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var filterBtnVisibility by remember { mutableStateOf(true) }

    filterBtnVisibility = searchable.value.isEmpty()

    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 1.dp,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            MainAppBarNavIcon(
                visible = filterBtnVisibility,
                cancelSearch = { cancelSearch(searchable, keyboardController) }
            )

            Row(
                modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        MaterialTheme.colors.onBackground.copy(alpha = 0.07f)
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = modifier.padding(start = 8.dp),
                    painter = painterResource(id = drawable.ic_round_search_24),
                    tint = MaterialTheme.colors.onBackground.copy(alpha = 0.35f),
                    contentDescription = null,
                )
                BasicTextField(
                    modifier = modifier.padding(start = 4.dp),
                    value = searchable.value,
                    onValueChange = { searchable.value = it },
                    textStyle = TextStyle(
                        color = MaterialTheme.colors.onBackground,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    //Adding placeholder for text field
                    decorationBox = { innerTextField ->
                        Box(Modifier.weight(1f)) {
                            if (searchable.value.isEmpty()) Text(
                                modifier = modifier.padding(start = 4.dp, end = 4.dp),
                                text = stringResource(id = string.hint_search_city),
                                style = LocalTextStyle.current.copy(
                                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.35f),
                                    fontSize = 20.sp
                                )
                            )
                            innerTextField()

                        }
                    }
                )
                Spacer(modifier.weight(1f))
                //Btn for clearing last entered symbol in text field
                AnimatedVisibility(
                    visible = !filterBtnVisibility,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    IconButton(onClick = {
                        searchable.value = searchable.value.dropLast(1)
                    }) {
                        Icon(
                            painter = painterResource(id = drawable.ic_round_clear_24),
                            tint = MaterialTheme.colors.onBackground.copy(alpha = 0.35f),
                            contentDescription = stringResource(
                                id = string.cd_scr_main_filter_btn
                            )
                        )
                    }
                }
            }

            ButtonFilterSearchList(filterBtnVisibility = filterBtnVisibility)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
private fun cancelSearch(
    search: MutableState<String>,
    keyboardController: SoftwareKeyboardController?
) {
    search.value = ""
    keyboardController?.hide()
}