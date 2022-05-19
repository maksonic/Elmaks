package ru.maksonic.elmaks.feature.main.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import ru.maksonic.elmaks.core.ui.theme.ElmaksTheme
import ru.maksonic.elmaks.core.ui.widget.LoadingViewState
import ru.maksonic.elmaks.feature.main.model.Msg
import ru.maksonic.elmaks.feature.main.update.MainViewModel
import ru.maksonic.elmaks.feature.main.widget.topbar.LoadingTopAppBar
import ru.maksonic.elmaks.feature.main.widget.topbar.MainTopAppBar

/**
 * @author maksonic on 01.05.2022
 */
internal typealias Message = (Msg) -> Unit

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen()
}

@Composable
fun MainScreen(
    isDarkMode: State<Boolean> = mutableStateOf(false),
    mainViewModel: MainViewModel = hiltViewModel()
) {
    MainUiScreen(mainViewModel, isDarkMode)
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun MainUiScreen(
    viewModel: MainViewModel,
    isDarkMode: State<Boolean>,
    modifier: Modifier = Modifier
) {
    val model = viewModel.featureModel.collectAsState()
    val sendMsg = viewModel::sendMsg
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            when {
                model.value.isLoading -> LoadingTopAppBar()
                model.value.isSuccessLoading -> {
                    MainTopAppBar(
                        model = model.value,
                        sendMsg = sendMsg,
                        keyboardController = keyboardController,
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
                model.value.isLoading -> LoadingViewState()
                model.value.isSuccessLoading -> SuccessCitiesViewState(model.value, sendMsg)
                model.value.isErrorLoading -> ErrorViewState(model.value, sendMsg)
            }
        }
    }
}