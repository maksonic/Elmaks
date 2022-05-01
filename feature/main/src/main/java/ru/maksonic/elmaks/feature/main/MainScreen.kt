package ru.maksonic.elmaks.feature.main

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import ru.maksonic.elmaks.feature.main.widget.CityItem
import ru.maksonic.elmaks.feature.main.widget.MainHeader
import ru.maksonic.elmaks.feature.main.widget.topbar.MainTopAppBar

/**
 * @author makosnic on 01.05.2022
 */
@Preview
@Composable
fun MainScreenPreview() {
    MainScreen({})
}

@Composable
fun MainScreen(
    onNavigateToDetails: () -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    MainUiScreen(onNavigateToDetails, viewModel)
}

@Composable
fun MainUiScreen(
    onNavigateToDetails: () -> Unit,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {

    val searchable = rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            MainTopAppBar(
                filterCityList = { },
                searchable = searchable
            )
        }
    ) {
        LazyColumn(Modifier.padding(it)) {
            item {
                MainHeader(searchable = searchable)
            }
            items(viewModel.list.value) { city ->
                CityItem(cityDomain = city, selectCity = onNavigateToDetails)
            }
        }
    }
}