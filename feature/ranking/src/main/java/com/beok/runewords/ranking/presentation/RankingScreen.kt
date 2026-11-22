package com.beok.runewords.ranking.presentation

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.beok.runewords.common.resource.iconResourceID
import com.beok.runewords.common.resource.nameResourceID
import com.beok.runewords.common.view.ContentLoading
import com.beok.runewords.ranking.R
import com.beok.runewords.ranking.presentation.model.RankingState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RankingRoute(
    onRuneClick: (String) -> Unit,
    onRuneWordClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RankingViewModel = hiltViewModel()
) {
    val state: RankingState by viewModel.rankingState.collectAsStateWithLifecycle()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.ranking_title),
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            RankingBody(state = state, onRuneClick = onRuneClick, onRuneWordClick = onRuneWordClick)
        }
    }
}

@Composable
private fun RankingBody(
    state: RankingState,
    onRuneClick: (String) -> Unit,
    onRuneWordClick: (String) -> Unit
) {
    when (state) {
        is RankingState.Content -> RankingContent(
            state = state,
            onRuneClick = onRuneClick,
            onRuneWordClick = onRuneWordClick
        )

        RankingState.Loading -> ContentLoading(isLoading = true)
        RankingState.Empty -> RankingMessage(textResID = R.string.ranking_empty)
        RankingState.Failed -> RankingMessage(textResID = R.string.ranking_failed)
        RankingState.None -> Unit
    }
}

@Composable
private fun RankingContent(
    state: RankingState.Content,
    onRuneClick: (String) -> Unit,
    onRuneWordClick: (String) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        if (state.periodDays > 0) {
            item {
                Text(
                    text = if (state.startDate.isNotEmpty() && state.endDate.isNotEmpty()) {
                        stringResource(
                            id = R.string.ranking_period_with_dates,
                            state.periodDays,
                            state.startDate,
                            state.endDate
                        )
                    } else {
                        stringResource(id = R.string.ranking_period, state.periodDays)
                    },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        if (state.runes.isNotEmpty()) {
            sectionHeader(titleResID = R.string.ranking_runes)
            itemsIndexed(items = state.runes, key = { _, rune -> "rune_${rune.name}" }) { index, rune ->
                RankingRow(rank = index + 1, onClick = { onRuneClick(rune.name) }) {
                    Image(
                        painter = painterResource(id = rune.iconResourceID),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .size(36.dp)
                    )
                    Text(
                        text = stringResource(id = rune.nameResourceID),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
        if (state.runeWords.isNotEmpty()) {
            sectionHeader(titleResID = R.string.ranking_rune_words)
            itemsIndexed(
                items = state.runeWords,
                key = { _, runeWord -> "rune_word_${runeWord.name}" }
            ) { index, runeWord ->
                RankingRow(rank = index + 1, onClick = { onRuneWordClick(runeWord.name) }) {
                    Text(
                        text = stringResource(id = runeWord.nameResourceID),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

private fun LazyListScope.sectionHeader(@StringRes titleResID: Int) {
    item(key = titleResID) {
        Text(
            text = stringResource(id = titleResID),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun RankingRow(rank: Int, onClick: () -> Unit, content: @Composable () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = rank.toString(),
            modifier = Modifier.width(32.dp),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        content()
    }
    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
}

@Composable
private fun RankingMessage(@StringRes textResID: Int) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(id = textResID),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
