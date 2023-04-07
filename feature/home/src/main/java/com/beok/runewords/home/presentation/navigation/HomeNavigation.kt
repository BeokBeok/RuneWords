package com.beok.runewords.home.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.beok.runewords.home.presentation.HomeScreen

const val homeNavigationRoute = "home"

fun NavController.navigateToHome() {
    navigate(route = homeNavigationRoute) {
        popUpTo(route = homeNavigationRoute) {
            inclusive = true
        }
    }
}

fun NavGraphBuilder.homeScreen(onRuneClick: (String) -> Unit) {
    composable(route = homeNavigationRoute) {
        HomeScreen(onRuneClick = onRuneClick)
    }
}
