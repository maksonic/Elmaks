package ru.maksonic.elmaks.feature.details

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.elmaks.feature.details.widget.DetailTopAppBar

/**
 * @author maksonic on 27.04.2022
 */
@Preview
@Composable
fun DetailsScreenPreview() {
    DetailsScreen({})
}

@Composable
fun DetailsScreen(
    onNavigateBack: () -> Unit
) {
    DetailsUiScreen(onNavigateBack)
}

@Composable
fun DetailsUiScreen(
    onNavigateBack: () -> Unit
) {
    Scaffold(topBar = {
        DetailTopAppBar(titleDetailCity = "Город", onNavigateBack)
    }) {

    }
}