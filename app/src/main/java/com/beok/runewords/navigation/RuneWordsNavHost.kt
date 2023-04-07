package com.beok.runewords.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.beok.runewords.combination.presentation.navigation.combinationScreen
import com.beok.runewords.combination.presentation.navigation.navigateToCombination
import com.beok.runewords.home.presentation.navigation.homeNavigationRoute
import com.beok.runewords.home.presentation.navigation.homeScreen

@Composable
fun RuneWordsNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = homeNavigationRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        homeScreen(onRuneClick = {
            navController.navigateToCombination(it)
        })
        combinationScreen()
    }
}
