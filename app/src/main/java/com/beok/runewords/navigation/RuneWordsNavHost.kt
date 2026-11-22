package com.beok.runewords.navigation

import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.beok.runewords.combination.presentation.navigation.Combination
import com.beok.runewords.combination.presentation.navigation.combinationScreen
import com.beok.runewords.combination.presentation.navigation.navigateToCombination
import com.beok.runewords.detail.presentation.navigation.detailScreen
import com.beok.runewords.detail.presentation.navigation.navigateToDetail
import com.beok.runewords.home.presentation.navigation.Home
import com.beok.runewords.home.presentation.navigation.homeScreen
import com.beok.runewords.info.presentation.navigation.navigateToRuneInfo
import com.beok.runewords.info.presentation.navigation.runeInfoScreen
import com.beok.runewords.ranking.presentation.navigation.navigateToRanking
import com.beok.runewords.ranking.presentation.navigation.rankingScreen

private const val TransitionDurationMillis = 300

@Composable
fun RuneWordsNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: Any = Home,
    showReviewWriteForm: () -> Unit,
    showInterstitial: (onFinished: () -> Unit) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = {
            slideIntoContainer(SlideDirection.Start, tween(TransitionDurationMillis))
        },
        exitTransition = {
            slideOutOfContainer(SlideDirection.Start, tween(TransitionDurationMillis))
        },
        popEnterTransition = {
            slideIntoContainer(SlideDirection.End, tween(TransitionDurationMillis))
        },
        popExitTransition = {
            slideOutOfContainer(SlideDirection.End, tween(TransitionDurationMillis))
        }
    ) {
        homeScreen(
            onRuneClick = navController::navigateToCombination,
            onRankingClick = navController::navigateToRanking
        )
        rankingScreen(
            onRuneClick = navController::navigateToCombination,
            onRuneWordClick = { runeWordName ->
                showInterstitial { navController.navigateToDetail(runeWordName) }
            }
        )
        combinationScreen(
            onRuneInfoClick = navController::navigateToRuneInfo,
            onRuneWordClick = { runeWordName ->
                showInterstitial { navController.navigateToDetail(runeWordName) }
            },
            showInterstitial = showInterstitial
        )
        runeInfoScreen()
        detailScreen(
            showReviewWriteForm = showReviewWriteForm,
            onRuneClick = {
                navController.navigateToCombination(it) {
                    popUpTo<Combination> {
                        inclusive = true
                    }
                }
            }
        )
    }
}
