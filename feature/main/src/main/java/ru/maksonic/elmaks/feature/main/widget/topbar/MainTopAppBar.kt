package ru.maksonic.elmaks.feature.main.widget.topbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.maksonic.elmaks.core.ui.theme.ElmaksTheme
import ru.maksonic.elmaks.core.ui.widget.IconActionButton
import ru.maksonic.elmaks.feature.main.update.MainViewModel
import ru.maksonic.elmaks.feature.main.view.Message
import ru.maksonic.elmaks.shared.R.drawable
import ru.maksonic.elmaks.shared.R.string

/**
 * @author maksonic on 01.05.2022
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun MainTopAppBar(
    msg: Message,
    modifier: Modifier = Modifier,
    searchable: MutableState<TextFieldValue>,
    isDarkMode: State<Boolean>,
    viewModel: MainViewModel

) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var filterBtnVisibility by remember { mutableStateOf(true) }
    val searchableLength = 50
    filterBtnVisibility = searchable.value.text.isEmpty()

    TopAppBar(
        backgroundColor = ElmaksTheme.color.background,
        elevation = ElmaksTheme.elevation.dp1,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            MainAppBarNavIcon(
                msg = msg,
                visible = filterBtnVisibility,
                cancelSearch = { cancelSearch(searchable, keyboardController) },
                viewModel = viewModel,
                isDarkMode = isDarkMode,
            )
            Row(
                modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(ElmaksTheme.padding.dp8)
                    .clip(ElmaksTheme.shape.cornerBig)
                    .background(ElmaksTheme.color.primaryVariant),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = modifier.padding(start = ElmaksTheme.padding.dp8),
                    painter = painterResource(id = drawable.ic_round_search_24),
                    tint = ElmaksTheme.color.controlNormal.copy(alpha = 0.35f),
                    contentDescription = null,
                )
                BasicTextField(
                    modifier = modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .padding(start = ElmaksTheme.padding.dp4),
                    value = searchable.value,
                    onValueChange = {
                        //Set max searchable text length
                        if (it.text.length < searchableLength) searchable.value = it
                    },
                    textStyle = TextStyle(
                        color = ElmaksTheme.color.primaryText,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    singleLine = true,
                    cursorBrush = SolidColor(ElmaksTheme.color.primary),
                    //Adding placeholder for text field
                    decorationBox = { innerTextField ->
                        Box(
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (searchable.value.text.isEmpty()) Text(
                                modifier = modifier.padding(
                                    start = ElmaksTheme.padding.dp4,
                                    end = ElmaksTheme.padding.dp4
                                ),
                                text = stringResource(id = string.hint_search_city),
                                style = LocalTextStyle.current.copy(
                                    color = ElmaksTheme.color.controlNormal.copy(alpha = 0.35f),
                                    fontSize = 20.sp
                                ),
                                maxLines = 1
                            )
                            innerTextField()

                        }
                    }
                )
                //    Spacer(modifier.weight(1f))
                //Btn for clearing entered symbols in text field
                AnimatedVisibility(
                    visible = !filterBtnVisibility,
                    enter = fadeIn(),
                    exit = fadeOut(),
                ) {
                    IconActionButton(onClick = {
                        searchable.value = searchable.value.copy(text = "")
                    }) {
                        Icon(
                            painter = painterResource(id = drawable.ic_round_clear_24),
                            tint = ElmaksTheme.color.controlNormal.copy(alpha = 0.35f),
                            contentDescription = stringResource(
                                id = string.cd_scr_main_filter_btn
                            )
                        )
                    }
                }
            }
            ButtonFilterSearchList(filterBtnVisibility, viewModel)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
private fun cancelSearch(
    searchable: MutableState<TextFieldValue>,
    keyboardController: SoftwareKeyboardController?
) {
    searchable.value = searchable.value.copy("")
    keyboardController?.hide()
}