package ru.maksonic.elmaks.navigation.impl

import androidx.compose.runtime.State
import androidx.navigation.*
import androidx.navigation.compose.composable
import ru.maksonic.elmaks.core.store.KeyStore
import ru.maksonic.elmaks.feature.details.view.DetailsScreen
import ru.maksonic.elmaks.feature.main.view.MainScreen
import ru.maksonic.elmaks.navigation.api.NavDestination
import javax.inject.Inject

/**
 * @Author maksonic on 18.05.2022
 */
class MainGraph @Inject constructor(private val keyStore: KeyStore) : GraphBuilder {

    override fun mainNavGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        isDarkMode: State<Boolean>,
    ) {
        navGraphBuilder.navigation(
            route = NavDestination.route,
            startDestination = NavDestination.Main.route
        ) {
            composable(
                NavDestination.Main.route,
            ) {
                MainScreen(isDarkMode = isDarkMode)
            }
            composable(
                NavDestination.Details.route.plus("/{${keyStore.passedCityIdKey}}")
            ) {
                DetailsScreen()
            }
        }
    }
}

