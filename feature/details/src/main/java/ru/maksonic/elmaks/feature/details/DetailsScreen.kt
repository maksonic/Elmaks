package ru.maksonic.elmaks.feature.details

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import ru.maksonic.elmaks.feature.details.widget.DetailTopAppBar

/**
 * @author maksonic on 27.04.2022
 */
@Preview
@Composable
fun DetailsScreenPreview() {
    DetailsScreen()
}

@Composable
fun DetailsScreen(viewModel: DetailsViewModel = hiltViewModel(), id: Long = 7) {
    DetailsUiScreen(viewModel, id = id)
}

@Composable
fun DetailsUiScreen(
    viewModel: DetailsViewModel,
    modifier: Modifier = Modifier,
    id: Long,
) {
    val cityName = viewModel.cityName.collectAsState()
    Scaffold(
        topBar = {
            DetailTopAppBar(titleDetailCity = id.toString(), viewModel)
        },
        modifier = modifier.systemBarsPadding()
    ) {

    }
}
