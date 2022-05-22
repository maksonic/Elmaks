package ru.maksonic.elmaks.feature.details.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
fun DetailsScreen() {
    val viewModel: DetailsViewModel = hiltViewModel()
    DetailsUiScreen(viewModel)
}

@Composable
private fun DetailsUiScreen(
    viewModel: DetailsViewModel,
    modifier: Modifier = Modifier,
) {
    val model = viewModel.featureModel.collectAsState()
    val sendMsg = viewModel::sendMsg

    Scaffold(
        topBar = {
            val title = if (model.value.isError) model.value.errorMessage else ""
            DetailTopAppBar(titleDetailCity = title, viewModel)
        },
        backgroundColor = ElmaksTheme.color.background,
        modifier = modifier.systemBarsPadding()
    ) { padding ->
        Column(modifier.padding(padding)) {
            DialogKladrInfo(state = model.value.isShowKladrDialog)

            when {
                model.value.isLoading -> LoadingViewState()
                model.value.isSuccessCityDetails -> SuccessDetailsViewState(model.value, sendMsg)
                model.value.isError -> ErrorViewState(model.value, sendMsg)
            }
        }
    }
}