package com.beok.runewords.detail.presentation.navigation

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.beok.runewords.detail.presentation.DetailRoute

fun NavController.navigateToDetail(runeWordName: String) {
    navigate(route = Detail(runeWordName = runeWordName.removeSuffix(suffix = "_"))) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.detailScreen(showReviewWriteForm: () -> Unit, onRuneClick: (String) -> Unit) {
    composable<Detail> {
        DetailRoute(
            modifier = Modifier.systemBarsPadding(),
            showReviewWriteForm = showReviewWriteForm,
            onRuneClick = onRuneClick
        )
    }
}
