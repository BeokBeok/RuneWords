package com.beok.runewords.combination.presentation.navigation

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.beok.runewords.combination.presentation.CombinationRoute

fun NavController.navigateToCombination(
    rune: String,
    builder: (NavOptionsBuilder.() -> Unit)? = null
) {
    navigate(route = Combination(rune = rune)) {
        if (builder != null) builder()
        launchSingleTop = true
    }
}

fun NavGraphBuilder.combinationScreen(
    onRuneInfoClick: (String) -> Unit,
    onRuneWordClick: (String) -> Unit
) {
    composable<Combination> {
        CombinationRoute(
            modifier = Modifier.systemBarsPadding(),
            onRuneInfoClick = onRuneInfoClick,
            onRuneWordClick = onRuneWordClick
        )
    }
}
