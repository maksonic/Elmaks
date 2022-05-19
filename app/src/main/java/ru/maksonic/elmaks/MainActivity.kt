package ru.maksonic.elmaks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.maksonic.elmaks.core.ui.theme.ElmaksTheme
import ru.maksonic.elmaks.navigation.api.NavDestination
import ru.maksonic.elmaks.navigation.api.Navigator
import ru.maksonic.elmaks.navigation.impl.GraphBuilder
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator
    @Inject
    lateinit var graphBuilder: GraphBuilder
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            navigator.navController = rememberNavController()
            val navController = navigator.navController
            val isDarkMode = viewModel.themeState.collectAsState()

            ElmaksTheme(darkTheme = isDarkMode.value) {
                NavHost(
                    navController = navController,
                    startDestination = NavDestination.route,
                ) {
                    graphBuilder.mainNavGraph(
                        navGraphBuilder = this,
                        navController = navController,
                        isDarkMode
                    )
                }
            }
        }
    }
}