package com.beok.runewords.combination.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.beok.runewords.combination.R
import com.beok.runewords.combination.domain.model.GameMode
import com.beok.runewords.combination.presentation.model.CombinationState
import com.beok.runewords.combination.presentation.model.RuneWordsItem
import com.beok.runewords.common.model.Rune
import com.beok.runewords.common.view.ContentLoading
import com.beok.runewords.tracking.LocalTracker
import com.beok.runewords.tracking.TrackingConstants
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun CombinationRoute(
    onRuneInfoClick: (String) -> Unit,
    onRuneWordClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CombinationViewModel = hiltViewModel()
) {
    val state: CombinationState by viewModel.combinationState.collectAsStateWithLifecycle()
    val rune: Rune = Rune.findByName(viewModel.rune) ?: return
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            if (state !is CombinationState.Content) return@Scaffold
            CombinationTopBar(rune = rune, onRuneInfoClick = onRuneInfoClick)
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            CombinationBody(state = state, onRuneWordClick = onRuneWordClick)
        }
    }
}

@Composable
private fun CombinationBody(state: CombinationState, onRuneWordClick: (String) -> Unit) {
    when (state) {
        is CombinationState.Content -> {
            CombinationContent(
                runeWords = state.value.toImmutableList(),
                onRuneWordClick = onRuneWordClick
            )
        }

        CombinationState.Loading -> {
            ContentLoading(isLoading = true)
        }

        CombinationState.Failed,
        CombinationState.None -> Unit
    }
}

@Composable
private fun CombinationContent(
    runeWords: ImmutableList<RuneWordsItem>,
    onRuneWordClick: (String) -> Unit
) {
    val tracking = LocalTracker.current
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items = runeWords, key = RuneWordsItem::name) { item ->
            val name = stringResource(id = item.nameResourceID)
            val runeWordsName = when (item.mode) {
                GameMode.STANDARD -> stringResource(id = R.string.standard_only, name)
                GameMode.LADDER -> stringResource(id = R.string.ladder_only, name)
                GameMode.ALL -> name
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onRuneWordClick(item.name)
                        tracking.logEvent(
                            name = TrackingConstants.Rune.WORDS_CLICK,
                            bundle = bundleOf(
                                TrackingConstants.Params.RUNE_WORDS_NAME to item.name
                            )
                        )
                    }
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = runeWordsName,
                    color = MaterialTheme.colorScheme.primary
                )
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CombinationTopBar(rune: Rune, onRuneInfoClick: (String) -> Unit) {
    val tracking = LocalTracker.current
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = rune.iconResourceID),
                    contentDescription = null,
                    modifier = Modifier.size(width = 40.dp, height = 40.dp)
                )
                Text(
                    text = stringResource(id = rune.nameResourceID),
                    modifier = Modifier.padding(start = 12.dp),
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    onRuneInfoClick(rune.name)
                    tracking.logEvent(
                        name = TrackingConstants.Rune.INFO_CLICK,
                        bundle = bundleOf(TrackingConstants.Params.RUNE_NAME to rune.name)
                    )
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}
