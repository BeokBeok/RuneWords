package com.beok.runewords.ranking.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.beok.runewords.ranking.presentation.RankingRoute

fun NavController.navigateToRanking() {
    navigate(route = Ranking) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.rankingScreen(onRuneClick: (String) -> Unit, onRuneWordClick: (String) -> Unit) {
    composable<Ranking> {
        RankingRoute(
            onRuneClick = onRuneClick,
            onRuneWordClick = onRuneWordClick
        )
    }
}
