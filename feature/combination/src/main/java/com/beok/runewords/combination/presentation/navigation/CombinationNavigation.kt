package com.beok.runewords.combination.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.beok.runewords.combination.presentation.CombinationRoute
import com.beok.runewords.common.BundleKeyConstants
import com.beok.runewords.common.model.Rune

const val combinationNavigationRoute = "combination"

fun NavController.navigateToCombination(rune: String) {
    navigate(route = "$combinationNavigationRoute/$rune") {
        launchSingleTop = true
        restoreState = true
    }
}

fun NavGraphBuilder.combinationScreen(onRuneInfoClick: (String) -> Unit) {
    composable(
        route = "$combinationNavigationRoute/{${BundleKeyConstants.RUNE_NAME}}",
        arguments = listOf(
            navArgument(BundleKeyConstants.RUNE_NAME) {
                type = NavType.StringType
            }
        )
    ) { navBackStackEntry ->
        CombinationRoute(
            rune = navBackStackEntry.arguments?.getString(BundleKeyConstants.RUNE_NAME)
                ?.let(Rune::findByName),
            onRuneInfoClick = onRuneInfoClick
        )
    }
}
