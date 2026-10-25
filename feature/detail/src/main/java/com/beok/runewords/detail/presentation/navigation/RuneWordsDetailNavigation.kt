package com.beok.runewords.detail.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.beok.runewords.common.navigation.DeepLinkBaseUrl
import com.beok.runewords.detail.presentation.DetailRoute

fun NavController.navigateToDetail(runeWordName: String) {
    navigate(route = Detail(runeWordName = runeWordName.removeSuffix(suffix = "_"))) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.detailScreen(showReviewWriteForm: () -> Unit, onRuneClick: (String) -> Unit) {
    composable<Detail>(
        deepLinks = listOf(navDeepLink<Detail>(basePath = "$DeepLinkBaseUrl/runewords"))
    ) {
        DetailRoute(
            showReviewWriteForm = showReviewWriteForm,
            onRuneClick = onRuneClick
        )
    }
}
