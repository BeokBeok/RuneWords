package com.beok.runewords.combination.presentation.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.beok.runewords.combination.presentation.CombinationRoute
import com.beok.runewords.common.BundleKeyConstants
import com.beok.runewords.common.model.Rune

const val combinationNavigationRoute = "combination"

fun NavController.navigateToCombination(rune: String, navOptions: NavOptions? = null) {
    navigate(
        route = "$combinationNavigationRoute/$rune",
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.combinationScreen() {
    composable(
        route = "$combinationNavigationRoute/{${BundleKeyConstants.RUNE_NAME}}",
        arguments = listOf(
            navArgument(BundleKeyConstants.RUNE_NAME) {
                type = NavType.StringType
            }
        )
    ) { navBackStackEntry ->
        CombinationRoute(
            navBackStackEntry.arguments?.getString(BundleKeyConstants.RUNE_NAME)
                ?.let(Rune::findByName)
        )
    }
}
