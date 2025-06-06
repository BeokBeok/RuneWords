package com.beok.runewords.home.presentation.navigation

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.beok.runewords.home.presentation.HomeScreen

const val HomeNavigationRoute = "home"

fun NavGraphBuilder.homeScreen(onRuneClick: (String) -> Unit) {
    composable(route = HomeNavigationRoute) {
        HomeScreen(
            modifier = Modifier.systemBarsPadding(),
            onRuneClick = onRuneClick
        )
    }
}
