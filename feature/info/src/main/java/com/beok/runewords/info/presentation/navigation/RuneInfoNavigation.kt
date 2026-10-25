package com.beok.runewords.info.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.beok.runewords.common.model.Rune
import com.beok.runewords.info.presentation.RuneInfoScreen

fun NavController.navigateToRuneInfo(rune: String, navOptions: NavOptions? = null) {
    navigate(
        route = RuneInfo(rune = rune),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.runeInfoScreen() {
    composable<RuneInfo> { navBackStackEntry ->
        RuneInfoScreen(
            rune = Rune.findByName(navBackStackEntry.toRoute<RuneInfo>().rune)
        )
    }
}
