package ru.maksonic.elmaks.navigation

import androidx.compose.runtime.State
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.maksonic.elmaks.feature.details.DetailsScreen
import ru.maksonic.elmaks.feature.main.view.MainScreen
import javax.inject.Inject

/**
 * @author maksonic on 01.05.2022
 */
interface Navigation {
    fun navGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        isDarkMode: State<Boolean>,
    )

    class BaseGraphBuilder @Inject constructor() : Navigation {
        override fun navGraph(
            navGraphBuilder: NavGraphBuilder,
            navController: NavHostController,
            isDarkMode: State<Boolean>,
        ) {
            navGraphBuilder.navigation(
                route = NavDestination.route,
                startDestination = NavDestination.Main.route
            ) {
                composable(NavDestination.Main.route) {
                    MainScreen(
                        onNavigateToDetails = {
                            navController.navigate(NavDestination.Details.route)
                        },
                        isDarkMode = isDarkMode
                    )
                }
                composable(NavDestination.Details.route) {

                    DetailsScreen(
                        onNavigateBack = { navController.navigateUp() }
                    )
                }
            }
        }
    }
}