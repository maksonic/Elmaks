package ru.maksonic.elmaks.feature.main.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import ru.maksonic.elmaks.core.ui.theme.ElmaksTheme
import ru.maksonic.elmaks.core.ui.widget.dropdown.CustomDropdownMenu
import ru.maksonic.elmaks.core.ui.widget.dropdown.CustomDropdownMenuItem
import ru.maksonic.elmaks.feature.main.update.MainViewModel
import ru.maksonic.elmaks.shared.R

/**
 * @author maksonic on 02.05.2022
 */
@Composable
internal fun DropdownItem(title: String, icon: Painter, modifier: Modifier = Modifier) {
    Row(
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            tint = ElmaksTheme.color.onSurface,
            contentDescription = null
        )
        Text(
            text = title,
            modifier = modifier.padding(start = ElmaksTheme.padding.dp8),
            style = MaterialTheme.typography.body1,
            color = ElmaksTheme.color.onSurface,
        )
    }
}

@Composable
fun FilterDropdownMenu(
    popupThemeMenu: MutableState<Boolean>,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier,
) {
    val dp8 = ElmaksTheme.padding.dp8

    CustomDropdownMenu(
        expanded = popupThemeMenu.value,
        onDismissRequest = {
            popupThemeMenu.value = false
        },
        offset = DpOffset(x = ElmaksTheme.padding.dp16, y = 0.dp),
        backgroundColor = ElmaksTheme.color.surface,
        shape = ElmaksTheme.shape.cornerBig,
        modifier = modifier
            .wrapContentSize()
            .background(
                color = ElmaksTheme.color.surface,
                shape = ElmaksTheme.shape.cornerBig
            )
    ) {
        CustomDropdownMenuItem(
            onClick = {
            //    viewModel.filterFromAtoZ()
                popupThemeMenu.value = false
            },
        ) {
            DropdownItem(
                title = stringResource(id = R.string.sorted_a_z),
                icon = painterResource(id = R.drawable.ic_sorted_a_z)
            )
        }
        Divider(modifier.padding(start = dp8, end = dp8))

        CustomDropdownMenuItem(
            onClick = {
          //      viewModel.filterFromZtoA()
                popupThemeMenu.value = false
            },
        ) {
            DropdownItem(
                title = stringResource(id = R.string.sorted_z_a),
                icon = painterResource(id = R.drawable.ic_sorted_z_a)
            )
        }
        Divider(modifier.padding(start = dp8, end = dp8))

        CustomDropdownMenuItem(
            onClick = {
            //    viewModel.filterPostalCodeAscending()
                popupThemeMenu.value = false
            },
        ) {
            DropdownItem(
                title = stringResource(id = R.string.sorted_code_asc),
                icon = painterResource(id = R.drawable.ic_sorted_code_to_up)
            )
        }

        Divider(modifier.padding(start = dp8, end = dp8))

        CustomDropdownMenuItem(
            onClick = {
             //   viewModel.filterPostalCodeDescending()
                popupThemeMenu.value = false
            },
        ) {
            DropdownItem(
                title = stringResource(id = R.string.sorted_code_desc),
                icon = painterResource(id = R.drawable.ic_sorted_code_to_down)
            )
        }
    }
}