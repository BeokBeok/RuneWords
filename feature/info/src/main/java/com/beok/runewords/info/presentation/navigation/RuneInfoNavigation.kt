package com.beok.runewords.info.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.beok.runewords.common.BundleKeyConstants
import com.beok.runewords.common.model.Rune
import com.beok.runewords.info.presentation.RuneInfoScreen

const val infoNavigationRoute = "runeInfo"

fun NavController.navigateToRuneInfo(rune: String, navOptions: NavOptions? = null) {
    navigate(
        route = "$infoNavigationRoute/$rune",
        navOptions = navOptions
    )
}

fun NavGraphBuilder.runeInfoScreen() {
    composable(
        route = "$infoNavigationRoute/{${BundleKeyConstants.RUNE_NAME}}",
        arguments = listOf(
            navArgument(BundleKeyConstants.RUNE_NAME) {
                type = NavType.StringType
            }
        )
    ) { navBackStackEntry ->
        RuneInfoScreen(
            rune = navBackStackEntry.arguments?.getString(BundleKeyConstants.RUNE_NAME)
                ?.let(Rune::findByName)
        )
    }
}
