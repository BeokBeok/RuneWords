package com.beok.runewords.info.presentation.navigation

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
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
            modifier = Modifier.systemBarsPadding(),
            rune = Rune.findByName(navBackStackEntry.toRoute<RuneInfo>().rune)
        )
    }
}
