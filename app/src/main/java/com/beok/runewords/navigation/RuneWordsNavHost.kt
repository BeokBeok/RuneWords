package com.beok.runewords.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.beok.runewords.combination.presentation.navigation.combinationScreen
import com.beok.runewords.combination.presentation.navigation.navigateToCombination
import com.beok.runewords.detail.presentation.navigation.detailScreen
import com.beok.runewords.detail.presentation.navigation.navigateToDetail
import com.beok.runewords.home.presentation.navigation.homeNavigationRoute
import com.beok.runewords.home.presentation.navigation.homeScreen
import com.beok.runewords.home.presentation.navigation.navigateToHome
import com.beok.runewords.info.presentation.navigation.navigateToRuneInfo
import com.beok.runewords.info.presentation.navigation.runeInfoScreen

@Composable
fun RuneWordsNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = homeNavigationRoute,
    showReviewWriteForm: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        homeScreen(onRuneClick = navController::navigateToCombination)
        combinationScreen(
            onRuneInfoClick = navController::navigateToRuneInfo,
            onRuneWordClick = navController::navigateToDetail,
            onBackClick = navController::navigateToHome
        )
        runeInfoScreen()
        detailScreen(
            showReviewWriteForm = showReviewWriteForm,
            onRuneClick = navController::navigateToCombination
        )
    }
}
