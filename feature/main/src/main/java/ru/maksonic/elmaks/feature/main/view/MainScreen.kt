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
import ru.maksonic.elmaks.core.ui.theme.ElmaksTheme
import ru.maksonic.elmaks.core.ui.widget.LoadingViewState
import ru.maksonic.elmaks.feature.main.model.Msg
import ru.maksonic.elmaks.feature.main.model.Model
import ru.maksonic.elmaks.feature.main.update.MainViewModel
import ru.maksonic.elmaks.feature.main.widget.CityItem
import ru.maksonic.elmaks.feature.main.widget.ErrorViewState
import ru.maksonic.elmaks.feature.main.widget.MainHeader
import ru.maksonic.elmaks.feature.main.widget.topbar.LoadingTopAppBar
import ru.maksonic.elmaks.feature.main.widget.topbar.MainTopAppBar

/**
 * @author maksonic on 01.05.2022
 */
internal typealias Message = (Msg) -> Unit

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen({})
}

@Composable
fun MainScreen(
    onNavigateToDetails: () -> Unit,
    isDarkMode: State<Boolean> = mutableStateOf(false),
    mainViewModel: MainViewModel = hiltViewModel()
) {
    MainUiScreen(onNavigateToDetails, mainViewModel, isDarkMode)
}

@Composable
private fun MainUiScreen(
    onNavigateToDetails: () -> Unit,
    viewModel: MainViewModel,
    isDarkMode: State<Boolean>,
    modifier: Modifier = Modifier
) {
    val model = viewModel.featureModel.collectAsState()
    val sendMsg = viewModel::sendMsg
    val searchable = remember { mutableStateOf(TextFieldValue(model.value.inputSearchCity)) }

    Scaffold(
        topBar = {
            when {
                model.value.isLoading -> {
                    LoadingTopAppBar()
                }
                model.value.isSuccessLoading -> {
                    MainTopAppBar(
                        sendMsg = sendMsg,
                        searchable = searchable,
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
                model.value.isSuccessLoading -> SuccessCityListViewState(model, sendMsg)
                model.value.isErrorLoading -> ErrorViewState(model, sendMsg)
            }
        }
    }
}

@Composable
private fun SuccessCityListViewState(model: State<Model>, sendMsg: Message) {

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = model.value.isRefreshing),
        onRefresh = { sendMsg(Msg.Ui.OnSwipeRefreshList) },
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
                MainHeader(searchable = mutableStateOf(TextFieldValue(model.value.inputSearchCity)))
            }
            items(model.value.cityList) { city ->
                CityItem(city = city, selectCity = {})
            }
        }
    }
}

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

