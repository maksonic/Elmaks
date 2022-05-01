package ru.maksonic.elmaks.feature.main.widget.topbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.maksonic.elmaks.shared.R.drawable
import ru.maksonic.elmaks.shared.R.string

/**
 * @author maksonic on 01.05.2022
 */
@Composable
fun ButtonFilterSearchList(modifier: Modifier = Modifier, filterBtnVisibility: Boolean) {
    val popupThemeMenu = remember { mutableStateOf(false) }

    AnimatedVisibility(visible = filterBtnVisibility) {
        IconButton(onClick = { popupThemeMenu.value = true }) {
            Icon(
                painter = painterResource(id = drawable.ic_round_filter_list_24),
                tint = MaterialTheme.colors.onBackground,
                contentDescription = stringResource(
                    id = string.cd_scr_main_filter_btn
                )
            )
        }

        Box() {
            DropdownMenu(
                expanded = popupThemeMenu.value,
                onDismissRequest = {
                    popupThemeMenu.value = false
                },
                modifier = modifier
                    .wrapContentWidth(Alignment.End)
                    .background(MaterialTheme.colors.surface)
            ) {
                DropdownMenuItem(
                    onClick = {
                        popupThemeMenu.value = false
                    },
                ) {
                    Row(modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(painter = painterResource(
                            id = drawable.ic_sorted_a_z),
                            contentDescription = null)
                        Text(
                            text = "От А до Я",
                            modifier = modifier.padding(start = 8.dp),
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                }
                Divider(modifier.padding(start = 8.dp, end = 8.dp))

                DropdownMenuItem(
                    onClick = {
                        popupThemeMenu.value = false
                    },
                ) {
                    Row(modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(painter = painterResource(
                            id = drawable.ic_sorted_z_a),
                            contentDescription = null)
                        Text(
                            text = "От Я до А",
                            modifier = modifier.padding(start = 8.dp),
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                }
                Divider(modifier.padding(start = 8.dp, end = 8.dp))

                DropdownMenuItem(
                    onClick = {
                        popupThemeMenu.value = false
                    },
                ) {
                    Row(modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(painter = painterResource(
                            id = drawable.ic_sorted_code_to_up),
                            contentDescription = null)
                        Text(
                            text = "Код по возрастанию",
                            modifier = modifier.padding(start = 8.dp),
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                }

                Divider(modifier.padding(start = 8.dp, end = 8.dp))

                DropdownMenuItem(
                    onClick = {
                        popupThemeMenu.value = false
                    },
                ) {
                    Row(modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(painter = painterResource(
                            id = drawable.ic_sorted_code_to_down),
                            contentDescription = null)
                        Text(
                            text = "Код по убыванию",
                            modifier = modifier.padding(start = 8.dp),
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                }
            }
        }
    }
}