package com.beok.runewords.combination.presentation.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.beok.runewords.combination.R

/**
 * 옵션 필터 바텀시트. 칩 선택은 [onApply]를 누를 때만 목록에 반영한다.
 * 고르면 결과가 0개가 되는 칩은 비활성으로 둔다.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun OptionFilterSheet(
    filter: OptionFilter,
    initialKeys: Set<String>,
    onApply: (keys: Set<String>, resultCount: Int) -> Unit,
    onDismiss: () -> Unit
) {
    var draft by rememberSaveable { mutableStateOf(initialKeys) }
    val resultCount = filter.matchingRuneWords(draft).size
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ) {
        OptionFilterHeader(selectedCount = draft.size, onReset = { draft = emptySet() })
        HorizontalDivider(modifier = Modifier.padding(top = 12.dp))
        Column(
            modifier = Modifier
                .weight(weight = 1f, fill = false)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            OptionFilterChips(
                chips = filter.chips,
                draft = draft,
                isEnabled = { key -> filter.matchingRuneWords(draft + key).isNotEmpty() },
                onToggle = { key -> draft = if (key in draft) draft - key else draft + key }
            )
        }
        HorizontalDivider()
        Button(
            onClick = { onApply(draft, resultCount) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = stringResource(id = R.string.option_filter_apply, resultCount))
        }
    }
}

@Composable
private fun OptionFilterHeader(selectedCount: Int, onReset: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.option_filter_title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(1f)
        )
        TextButton(onClick = onReset, enabled = selectedCount > 0) {
            Text(text = stringResource(id = R.string.option_filter_reset))
        }
    }
    Text(
        text = if (selectedCount == 0) {
            stringResource(id = R.string.option_filter_hint_empty)
        } else {
            stringResource(id = R.string.option_filter_hint_selected, selectedCount)
        },
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun OptionFilterChips(
    chips: List<OptionFilterChip>,
    draft: Set<String>,
    isEnabled: (String) -> Boolean,
    onToggle: (String) -> Unit
) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        chips.forEach { chip ->
            val selected = chip.key in draft
            FilterChip(
                selected = selected,
                onClick = { onToggle(chip.key) },
                label = {
                    Text(text = stringResource(R.string.option_filter_chip, chip.label, chip.runeWordCount))
                },
                enabled = selected || isEnabled(chip.key),
                leadingIcon = if (selected) {
                    {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )
                    }
                } else {
                    null
                }
            )
        }
    }
}
