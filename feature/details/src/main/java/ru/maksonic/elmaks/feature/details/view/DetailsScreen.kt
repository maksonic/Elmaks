package ru.maksonic.elmaks.feature.details.view

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import ru.maksonic.elmaks.core.ui.theme.ElmaksTheme
import ru.maksonic.elmaks.core.ui.widget.LoadingViewState
import ru.maksonic.elmaks.feature.details.model.Msg
import ru.maksonic.elmaks.feature.details.update.DetailsViewModel
import ru.maksonic.elmaks.feature.details.widget.DetailTopAppBar
import ru.maksonic.elmaks.feature.details.widget.DialogKladrInfo

/**
 * @author maksonic on 27.04.2022
 */
internal typealias Message = (Msg) -> Unit

@Composable
fun DetailsScreen(isDarkMode: State<Boolean>) {
    val viewModel: DetailsViewModel = hiltViewModel()

    DetailsUiScreen(viewModel, isDarkMode)
}

@Composable
private fun DetailsUiScreen(
    viewModel: DetailsViewModel,
    isDarkMode: State<Boolean>,
    modifier: Modifier = Modifier,
) {
    val model = viewModel.featureModel.collectAsState()
    val sendMsg = viewModel::sendMsg

    /*SideEffect {
        sendMsg(Msg.Internal.GenerateCardColor(isDarkMode))
    }*/
    Scaffold(
        topBar = {
            val title = if (model.value.isError) model.value.errorMessage else ""
            DetailTopAppBar(titleDetailCity = title, viewModel)
        },
        backgroundColor = ElmaksTheme.color.background,
        modifier = modifier.systemBarsPadding()
    ) { padding ->

        DialogKladrInfo(state = model.value.isShowKladrDialog)
        when {
            model.value.isLoading -> LoadingViewState()
            model.value.isSuccessCityDetails -> SuccessDetailsViewState(model.value, sendMsg)
            model.value.isError -> ErrorViewState(model.value, sendMsg)
        }
    }
}