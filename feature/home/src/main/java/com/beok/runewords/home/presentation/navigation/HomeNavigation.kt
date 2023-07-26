package com.beok.runewords.home.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.beok.runewords.home.presentation.HomeScreen

const val HomeNavigationRoute = "home"

fun NavGraphBuilder.homeScreen(onRuneClick: (String) -> Unit) {
    composable(route = HomeNavigationRoute) {
        HomeScreen(onRuneClick = onRuneClick)
    }
}
