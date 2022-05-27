package ru.maksonic.elmaks.feature.main.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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

@Composable
fun MainScreen(
    isDarkMode: State<Boolean> = mutableStateOf(false),
) {
    val viewModel: MainViewModel = hiltViewModel()
    MainUiScreen(viewModel, isDarkMode)
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
                model.value.isSuccess -> {
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
                model.value.isSuccess -> SuccessCitiesViewState(model.value, sendMsg)
                model.value.isError -> ErrorViewState(model.value, sendMsg)
            }
        }
    }
}