package ru.maksonic.elmaks.feature.main.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay
import ru.maksonic.elmaks.core.ui.theme.ElmaksTheme
import ru.maksonic.elmaks.core.ui.widget.LoadingViewState
import ru.maksonic.elmaks.feature.main.model.MainFeature
import ru.maksonic.elmaks.feature.main.update.MainViewModel
import ru.maksonic.elmaks.feature.main.widget.CityItem
import ru.maksonic.elmaks.feature.main.widget.ErrorViewState
import ru.maksonic.elmaks.feature.main.widget.MainHeader
import ru.maksonic.elmaks.feature.main.widget.topbar.LoadingTopAppBar
import ru.maksonic.elmaks.feature.main.widget.topbar.MainTopAppBar

/**
 * @author maksonic on 01.05.2022
 */
internal typealias Message = (MainFeature.Msg) -> Unit
internal typealias Model = State<MainFeature.Model>

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen({})
}

@Composable
fun MainScreen(
    onNavigateToDetails: () -> Unit,
    isDarkMode: State<Boolean> = mutableStateOf(false),
    viewModel: MainViewModel = hiltViewModel()
) {
    MainUiScreen(onNavigateToDetails, viewModel, isDarkMode)
}

@Composable
fun MainUiScreen(
    onNavigateToDetails: () -> Unit,
    viewModel: MainViewModel,
    isDarkMode: State<Boolean>,
    modifier: Modifier = Modifier
) {
    val searchable = remember { mutableStateOf(TextFieldValue("")) }
    val model = viewModel.featureModel.collectAsState()
    val msg = viewModel::sendMsg

    Scaffold(
        topBar = {
            when {
                model.value.isLoading -> {
                    LoadingTopAppBar()
                }
                model.value.isSuccessLoading -> {
                    MainTopAppBar(
                        msg = msg,
                        searchable = searchable,
                        viewModel = viewModel,
                        isDarkMode = isDarkMode
                    )
                }
            }
        },
        backgroundColor = ElmaksTheme.color.background,
        modifier = modifier.systemBarsPadding()
    ) { paddingValues ->
        Column(modifier.padding(paddingValues)) {
            when {
                model.value.isLoading -> LoadingViewState(modifier.padding())
                model.value.isSuccessLoading -> SuccessCityListViewState(model, msg)
                model.value.isErrorLoading -> ErrorViewState(model, msg)
            }
        }
        /*val citiesList = viewModel.list.value
        var filteredList: List<CityUi>

        LazyColumn(modifier.padding(it)) {

            filteredList = if (searchable.value.text.isEmpty()) {
                citiesList
            } else {
                val resultList = mutableListOf<CityUi>()
                for (city in citiesList) {
                    if (
                        city.name.lowercase().contains(searchable.value.text.lowercase()) ||
                        city.postalCode.toString().contains(searchable.value.text)
                    ) {
                        resultList.add(city)
                    }
                }
                resultList
            }

            item {
                MainHeader(searchable = searchable)
            }
            if (filteredList.isNotEmpty()) {
                items(filteredList) { city ->
                    CityItem(cityDomain = city, selectCity = onNavigateToDetails)
                }
            } else {
                item { EmptySearchWidget() }
            }
        }*/
    }
}

@Composable
fun SuccessCityListViewState(model: Model, msg: Message) {

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = model.value.isRefreshing),
        onRefresh = { msg(MainFeature.Msg.OnSwipeRefreshList) },
        indicator = { state, trigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = trigger,
                scale = true,
                contentColor = ElmaksTheme.color.primary,
                backgroundColor = ElmaksTheme.color.surface,
            )
        }
    ) {
        LazyColumn {
            item {
                MainHeader(searchable = mutableStateOf(TextFieldValue("")))
            }
            items(model.value.cityList) { city ->
                CityItem(city = city, selectCity = {})
            }
        }
    }
}
/*  filteredList = if (searchable.value.text.isEmpty()) {
      citiesList
  } else {
      val resultList = mutableListOf<CityUi>()
      for (city in citiesList) {
          if (
              city.name.lowercase().contains(searchable.value.text.lowercase()) ||
              city.postalCode.toString().contains(searchable.value.text)
          ) {
              resultList.add(city)
          }
      }
      resultList
  }
*/

/*if (filteredList.isNotEmpty()) {
    items(filteredList) { city ->
        CityItem(city = city, selectCity = onNavigateToDetails)
    }
} else {
    item { EmptySearchWidget() }
}*/

@Composable
fun EmptySearchWidget(modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "\uD83D\uDE22", fontSize = 56.sp)
        Text(text = "Мы ничего не нашли(", style = ElmaksTheme.typography.title)
    }
}

