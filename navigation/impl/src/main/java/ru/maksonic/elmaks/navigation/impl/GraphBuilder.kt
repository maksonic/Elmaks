package ru.maksonic.elmaks.navigation.impl

import androidx.compose.runtime.State
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

/**
 * @author maksonic on 01.05.2022
 */
interface GraphBuilder {
    fun mainNavGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        isDarkMode: State<Boolean>
    )
}