package com.beok.runewords.home.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.beok.runewords.home.presentation.HomeScreen

const val homeNavigationRoute = "home"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    navigate(route = homeNavigationRoute, navOptions = navOptions)
}

fun NavGraphBuilder.homeScreen() {
    composable(route = homeNavigationRoute) {
        HomeScreen()
    }
}
