package com.beok.runewords.combination.presentation.navigation

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.beok.runewords.combination.presentation.CombinationRoute
import com.beok.runewords.common.navigation.DeepLinkBaseUrl

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
    composable<Combination>(
        deepLinks = listOf(navDeepLink<Combination>(basePath = "$DeepLinkBaseUrl/rune"))
    ) {
        CombinationRoute(
            modifier = Modifier.systemBarsPadding(),
            onRuneInfoClick = onRuneInfoClick,
            onRuneWordClick = onRuneWordClick
        )
    }
}
