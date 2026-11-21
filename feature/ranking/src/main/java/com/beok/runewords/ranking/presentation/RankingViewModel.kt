package com.beok.runewords.ranking.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beok.runewords.common.ext.resourceIDByName
import com.beok.runewords.common.model.Result
import com.beok.runewords.common.model.Rune
import com.beok.runewords.common.model.asResult
import com.beok.runewords.ranking.domain.RankingRepository
import com.beok.runewords.ranking.domain.model.RuneRanking
import com.beok.runewords.ranking.presentation.model.RankingRuneWordItem
import com.beok.runewords.ranking.presentation.model.RankingState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
internal class RankingViewModel @Inject constructor(
    rankingRepository: RankingRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    val rankingState: StateFlow<RankingState> = rankingRepository.fetchRanking()
        .asResult()
        .map { rankingResult ->
            when (rankingResult) {
                is Result.Error -> RankingState.Failed
                Result.Loading -> RankingState.Loading
                is Result.Success -> rankingResult.data.toState()
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = RankingState.None
        )

    // 앱이 모르는 이름(새 룬워드, 오타 등)은 표시하지 않습니다.
    private fun RuneRanking.toState(): RankingState {
        val runes = runes.mapNotNull(Rune::findByName).distinct()
        val runeWords = runeWords
            .map { RankingRuneWordItem(name = it, nameResourceID = context.resourceIDByName(it)) }
            .filter { it.nameResourceID > 0 }
        if (runes.isEmpty() && runeWords.isEmpty()) return RankingState.Empty
        return RankingState.Content(
            periodDays = periodDays,
            startDate = startDate,
            endDate = endDate,
            runes = runes.toImmutableList(),
            runeWords = runeWords.toImmutableList()
        )
    }
}
