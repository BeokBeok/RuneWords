package com.beok.runewords.combination.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.beok.runewords.combination.presentation.CombinationRoute
import com.beok.runewords.common.BundleKeyConstants

const val CombinationNavigationRoute = "combination"

fun NavController.navigateToCombination(
    rune: String,
    builder: (NavOptionsBuilder.() -> Unit)? = null
) {
    navigate(route = "$CombinationNavigationRoute/$rune") {
        if (builder != null) builder()
        launchSingleTop = true
    }
}

fun NavGraphBuilder.combinationScreen(
    onRuneInfoClick: (String) -> Unit,
    onRuneWordClick: (String) -> Unit,
) {
    composable(
        route = "$CombinationNavigationRoute/{${BundleKeyConstants.RUNE_NAME}}",
        arguments = listOf(
            navArgument(BundleKeyConstants.RUNE_NAME) {
                type = NavType.StringType
            }
        )
    ) {
        CombinationRoute(
            onRuneInfoClick = onRuneInfoClick,
            onRuneWordClick = onRuneWordClick,
        )
    }
}
