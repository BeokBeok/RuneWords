package com.beok.runewords.home.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.beok.runewords.home.presentation.HomeScreen

fun NavGraphBuilder.homeScreen(onRuneClick: (String) -> Unit) {
    composable<Home> {
        HomeScreen(
            onRuneClick = onRuneClick
        )
    }
}
