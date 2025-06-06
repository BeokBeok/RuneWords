package com.beok.runewords.info.presentation.navigation

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.beok.runewords.common.BundleKeyConstants
import com.beok.runewords.common.model.Rune
import com.beok.runewords.info.presentation.RuneInfoScreen

const val InfoNavigationRoute = "runeInfo"

fun NavController.navigateToRuneInfo(rune: String, navOptions: NavOptions? = null) {
    navigate(
        route = "$InfoNavigationRoute/$rune",
        navOptions = navOptions
    )
}

fun NavGraphBuilder.runeInfoScreen() {
    composable(
        route = "$InfoNavigationRoute/{${BundleKeyConstants.RUNE_NAME}}",
        arguments = listOf(
            navArgument(BundleKeyConstants.RUNE_NAME) {
                type = NavType.StringType
            }
        )
    ) { navBackStackEntry ->
        RuneInfoScreen(
            modifier = Modifier.systemBarsPadding(),
            rune = navBackStackEntry.arguments?.getString(BundleKeyConstants.RUNE_NAME)
                ?.let(Rune::findByName)
        )
    }
}
