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
import ru.maksonic.elmaks.navigation.NavDestination
import ru.maksonic.elmaks.navigation.Navigation
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigation: Navigation

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberNavController()
            val isDarkMode = viewModel.themeState.collectAsState()

            ElmaksTheme(darkTheme = isDarkMode.value) {
                NavHost(
                    navController = navController,
                    startDestination = NavDestination.route
                ) {
                    navigation.navGraph(
                        navGraphBuilder = this,
                        navController = navController,
                        isDarkMode
                    )
                }
            }
        }
    }
}