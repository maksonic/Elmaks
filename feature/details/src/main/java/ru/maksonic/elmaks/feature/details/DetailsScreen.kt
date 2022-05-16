package ru.maksonic.elmaks.feature.details

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    ) {
    Scaffold(
        topBar = {
            DetailTopAppBar(titleDetailCity = "Город", onNavigateBack)
        },
        modifier = modifier.systemBarsPadding()
    ) {

    }
}
