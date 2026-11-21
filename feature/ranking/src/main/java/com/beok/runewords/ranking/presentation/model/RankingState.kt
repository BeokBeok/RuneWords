package com.beok.runewords.ranking.presentation.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.beok.runewords.common.model.Rune
import kotlinx.collections.immutable.ImmutableList

internal sealed interface RankingState {

    data object None : RankingState

    data object Loading : RankingState

    @Immutable
    data class Content(
        val periodDays: Int,
        val runes: ImmutableList<Rune>,
        val runeWords: ImmutableList<RankingRuneWordItem>
    ) : RankingState

    /** 아직 집계 결과가 없거나 표시할 수 있는 항목이 없다. */
    data object Empty : RankingState

    data object Failed : RankingState
}

@Immutable
internal data class RankingRuneWordItem(val name: String, @StringRes val nameResourceID: Int)
