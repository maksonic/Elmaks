package ru.maksonic.elmaks.navigation.impl

import androidx.compose.runtime.State
import androidx.navigation.*
import androidx.navigation.compose.composable
import ru.maksonic.elmaks.feature.details.DetailsScreen
import ru.maksonic.elmaks.feature.main.view.MainScreen
import ru.maksonic.elmaks.navigation.api.NavDestination
import javax.inject.Inject

/**
 * @Author maksonic on 18.05.2022
 */
class MainGraph @Inject constructor() : GraphBuilder {

    override fun mainNavGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        isDarkMode: State<Boolean>,
    ) {
        navGraphBuilder.navigation(
            route = NavDestination.route,
            startDestination = NavDestination.Main.route
        ) {
            composable(NavDestination.Main.route,
            ) {
                MainScreen(isDarkMode = isDarkMode)
            }
            composable(NavDestination.Details.route,
                arguments = listOf(navArgument("cityId") {
                    type = NavType.LongType
                })) {  backStackEntry ->
                val args = backStackEntry.arguments?.getLong("cityId")
                DetailsScreen(id = args ?: 444)
            }
        }
    }
}

