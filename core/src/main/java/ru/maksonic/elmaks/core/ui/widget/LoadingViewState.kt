package ru.maksonic.elmaks.core.ui.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.maksonic.elmaks.core.ui.theme.ElmaksTheme

/**
 * @author maksonic on 12.05.2022
 */
@Composable
fun LoadingViewState(modifier: Modifier = Modifier) {
    Box(
        modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier.size(150.dp),
            color = ElmaksTheme.color.primary,
            strokeWidth = ElmaksTheme.componentSize.circularProgressIndicatorStrokeWidth
        )
    }
}