package com.beok.runewords.combination.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.beok.runewords.combination.R
import com.beok.runewords.combination.domain.model.GameMode
import com.beok.runewords.combination.presentation.filter.OptionFilter
import com.beok.runewords.combination.presentation.filter.OptionFilterSheet
import com.beok.runewords.combination.presentation.model.CombinationState
import com.beok.runewords.combination.presentation.model.RuneWordsItem
import com.beok.runewords.common.model.Rune
import com.beok.runewords.common.option.OptionFilterLabel
import com.beok.runewords.common.option.RuneWordOptionCatalog
import com.beok.runewords.common.option.RuneWordOptionSection
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
    val tracking = LocalTracker.current
    val filter = rememberOptionFilter(runeWords = (state as? CombinationState.Content)?.value)
    var selectedKeys by rememberSaveable { mutableStateOf(emptySet<String>()) }
    var isFilterOpen by rememberSaveable { mutableStateOf(false) }
    val activeKeys = filter?.validKeys(selectedKeys).orEmpty()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            if (state !is CombinationState.Content) return@Scaffold
            CombinationTopBar(
                rune = rune,
                filterCount = activeKeys.size,
                onFilterClick = {
                    isFilterOpen = true
                    tracking.logEvent(
                        name = TrackingConstants.OptionFilter.OPEN,
                        bundle = bundleOf(TrackingConstants.Params.RUNE_NAME to rune.name)
                    )
                },
                onRuneInfoClick = onRuneInfoClick
            )
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            CombinationBody(
                state = state,
                filter = filter,
                activeKeys = activeKeys,
                onRemoveKey = { selectedKeys = activeKeys - it },
                onRuneWordClick = onRuneWordClick
            )
        }
    }
    if (isFilterOpen && filter != null) {
        OptionFilterSheet(
            filter = filter,
            initialKeys = activeKeys,
            onApply = { keys, resultCount ->
                selectedKeys = keys
                isFilterOpen = false
                tracking.logEvent(
                    name = TrackingConstants.OptionFilter.APPLY,
                    bundle = bundleOf(
                        TrackingConstants.Params.RUNE_NAME to rune.name,
                        TrackingConstants.Params.OPTIONS to keys.sorted().joinToString(","),
                        TrackingConstants.Params.RESULT_COUNT to resultCount.toLong()
                    )
                )
            },
            onDismiss = { isFilterOpen = false }
        )
    }
}

@Composable
private fun rememberOptionFilter(runeWords: List<RuneWordsItem>?): OptionFilter? {
    val configuration = LocalConfiguration.current
    val resources = LocalContext.current.resources
    return remember(runeWords, configuration) {
        runeWords ?: return@remember null
        OptionFilter(
            optionsByRuneWord = runeWords.associate { item ->
                item.name to RuneWordOptionCatalog.find(item.name).orEmpty().flatMap(RuneWordOptionSection::options)
            },
            labelOf = { type -> OptionFilterLabel.label(type, resources::getString) }
        )
    }
}

@Composable
private fun CombinationBody(
    state: CombinationState,
    filter: OptionFilter?,
    activeKeys: Set<String>,
    onRemoveKey: (String) -> Unit,
    onRuneWordClick: (String) -> Unit
) {
    when (state) {
        is CombinationState.Content -> {
            val matching = filter?.matchingRuneWords(activeKeys)
            Column {
                if (filter != null && activeKeys.isNotEmpty()) {
                    ActiveFilterRow(filter = filter, activeKeys = activeKeys, onRemoveKey = onRemoveKey)
                }
                CombinationContent(
                    runeWords = state.value
                        .filter { matching == null || it.name in matching }
                        .toImmutableList(),
                    tagsOf = { item -> filter?.matchedTags(item.name, activeKeys).orEmpty() },
                    onRuneWordClick = onRuneWordClick
                )
            }
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
    tagsOf: @Composable (RuneWordsItem) -> List<String>,
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
                RuneWordsTags(tags = tagsOf(item))
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
private fun CombinationTopBar(
    rune: Rune,
    filterCount: Int,
    onFilterClick: () -> Unit,
    onRuneInfoClick: (String) -> Unit
) {
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
            IconButton(onClick = onFilterClick) {
                BadgedBox(
                    badge = {
                        if (filterCount > 0) Badge { Text(text = filterCount.toString()) }
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_filter_list),
                        contentDescription = stringResource(id = R.string.option_filter_open),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
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

@Composable
private fun OptionFilter.matchedTags(runeWordName: String, keys: Set<String>): List<String> = chips
    .filter { it.key in keys }
    .map { chip ->
        val skills = matchedSkills(runeWordName, chip.key)
        if (skills.isEmpty()) return@map chip.label
        stringResource(
            id = R.string.option_filter_tag,
            chip.label,
            skills.map { stringResource(id = it.nameResID) }.joinToString(", ")
        )
    }

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ActiveFilterRow(filter: OptionFilter, activeKeys: Set<String>, onRemoveKey: (String) -> Unit) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        filter.chips.filter { it.key in activeKeys }.forEach { chip ->
            val description = stringResource(id = R.string.option_filter_remove, chip.label)
            InputChip(
                selected = true,
                onClick = { onRemoveKey(chip.key) },
                label = { Text(text = chip.label) },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = description,
                        modifier = Modifier.size(InputChipDefaults.IconSize)
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun RuneWordsTags(tags: List<String>) {
    if (tags.isEmpty()) return
    FlowRow(
        modifier = Modifier.padding(top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        tags.forEach { tag ->
            Text(
                text = tag,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            )
        }
    }
}
