package com.beok.runewords.detail.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.beok.runewords.common.BundleKeyConstants
import com.beok.runewords.detail.presentation.DetailRoute

const val DetailNavigationRoute = "detail"

fun NavController.navigateToDetail(runeWordName: String) {
    navigate(route = "$DetailNavigationRoute/$runeWordName") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.detailScreen(
    showReviewWriteForm: () -> Unit,
    onRuneClick: (String) -> Unit
) {
    composable(
        route = "$DetailNavigationRoute/{${BundleKeyConstants.RUNE_WORDS_NAME}}",
        arguments = listOf(
            navArgument(BundleKeyConstants.RUNE_WORDS_NAME) {
                type = NavType.StringType
            }
        )
    ) {
        DetailRoute(
            showReviewWriteForm = showReviewWriteForm,
            onRuneClick = onRuneClick
        )
    }
}
