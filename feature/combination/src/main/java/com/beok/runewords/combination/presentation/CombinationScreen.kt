package com.beok.runewords.combination.presentation

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.beok.runewords.combination.R
import com.beok.runewords.combination.domain.model.RuneWords
import com.beok.runewords.combination.presentation.vo.CombinationState
import com.beok.runewords.common.ext.resourceIDByName
import com.beok.runewords.common.model.Rune
import com.beok.runewords.common.view.ContentLoading
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun CombinationRoute(
    rune: Rune?,
    viewModel: CombinationViewModel = hiltViewModel()
) {
    if (rune == null) return
    LaunchedEffect(key1 = Unit) {
        viewModel.fetchRuneWords(rune)
    }

    val state = viewModel.state
    Column(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is CombinationState.Content -> {
                CombinationTopBar(rune = rune)
                CombinationContent(runeWords = state.value.toImmutableList())
            }
            CombinationState.Loading -> {
                ContentLoading(isLoading = true)
            }
            CombinationState.Failed,
            CombinationState.None -> Unit
        }
    }
}

@Composable
private fun CombinationContent(runeWords: ImmutableList<RuneWords>) {
    val context: Context = LocalContext.current
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(runeWords) { item ->
            if (context.resourceIDByName(item.name) > 0) {
                val isLadder = stringArrayResource(id = R.array.ladder_rune_words)
                    .contains(item.name)
                var runeWordsName =
                    stringResource(id = context.resourceIDByName(item.name))
                if (isLadder) {
                    runeWordsName = stringResource(id = R.string.ladder_only, runeWordsName)
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                        }
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = runeWordsName)
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun CombinationTopBar(rune: Rune) {
    TopAppBar(
        title = {
            Image(
                painter = painterResource(id = rune.iconResourceID),
                contentDescription = null,
                modifier = Modifier.size(width = 40.dp, height = 40.dp)
            )
            Text(
                text = stringResource(id = rune.nameResourceID),
                modifier = Modifier.padding(start = 12.dp),
                fontSize = 20.sp
            )
            RuneInfoIcon(rune = rune)
        }
    )
}

@Composable
private fun RuneInfoIcon(rune: Rune) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            modifier = Modifier
                .clickable {
                }
                .padding(12.dp),
            imageVector = Icons.Default.Info,
            contentDescription = null,
        )
    }
}
