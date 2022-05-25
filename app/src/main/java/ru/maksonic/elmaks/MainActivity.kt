package ru.maksonic.elmaks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.maksonic.elmaks.core.ui.theme.ElmaksTheme
import ru.maksonic.elmaks.navigation.api.NavDestination
import ru.maksonic.elmaks.navigation.api.CityNavigator
import ru.maksonic.elmaks.navigation.impl.GraphBuilder
import ru.maksonic.elmaks.navigation.impl.MainGraph
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var cityNavigator: CityNavigator
    @Inject
    lateinit var graphBuilder: MainGraph
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            cityNavigator.navController = rememberNavController()
            val navController = cityNavigator.navController
            val isDarkMode = viewModel.themeState.collectAsState()

            ElmaksTheme(darkTheme = isDarkMode.value) {
                NavHost(
                    navController = navController,
                    startDestination = NavDestination.route,
                ) {
                    graphBuilder.mainNavGraph(
                        navGraphBuilder = this,
                        navController = navController,
                        isDarkMode = isDarkMode
                    )
                }
            }
        }
    }
}