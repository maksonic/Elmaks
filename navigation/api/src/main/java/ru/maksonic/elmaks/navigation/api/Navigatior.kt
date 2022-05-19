package ru.maksonic.elmaks.navigation.api

import androidx.navigation.NavHostController
import ru.maksonic.elmaks.shared.CityUi
import javax.inject.Inject

/**
 * @Author maksonic on 18.05.2022
 */
class Navigator @Inject constructor() : Router {
    lateinit var navController: NavHostController

    override fun backPressed() = navController.popBackStack()

    override fun navigateToDetails(cityId: Long) {
        navController.navigate(NavDestination.Details.route.plus("5555"))
    }

    fun fetchPassedData(): Long {
        return navController.previousBackStackEntry?.arguments?.getLong("cityId") ?: 0L
    }
}