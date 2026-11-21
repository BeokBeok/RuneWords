package com.beok.runewords.ranking.presentation

import android.content.Context
import com.beok.runewords.common.model.Rune
import com.beok.runewords.common.test.MainCoroutineTestExtension
import com.beok.runewords.ranking.domain.RankingRepository
import com.beok.runewords.ranking.domain.model.RuneRanking
import com.beok.runewords.ranking.presentation.model.RankingState
import io.mockk.every
import io.mockk.mockk
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineTestExtension::class)
internal class RankingViewModelTest {

    private val context: Context = mockk {
        every { packageName } returns "com.beok.runewords"
        every { resources } returns mockk {
            every { getIdentifier("enigma", "string", any()) } returns 1
            every { getIdentifier("unknown_rune_word", "string", any()) } returns 0
        }
    }

    @Test
    fun `앱이 아는 룬과 룬워드만 순위대로 표시합니다`() = runTest {
        // given
        val viewModel = RankingViewModel(
            rankingRepository = RankingRepository.Fake(
                RuneRanking(
                    periodDays = 7,
                    startDate = "2026-11-14",
                    endDate = "2026-11-20",
                    runes = listOf("ber", "unknown_rune", "JAH"),
                    runeWords = listOf("unknown_rune_word", "enigma")
                )
            ),
            context = context
        )

        // when
        val state = viewModel.rankingState.first { it is RankingState.Content } as RankingState.Content

        // then
        assertEquals(expected = 7, actual = state.periodDays)
        assertEquals(expected = "2026-11-14", actual = state.startDate)
        assertEquals(expected = listOf(Rune.BER, Rune.JAH), actual = state.runes)
        assertEquals(expected = listOf("enigma"), actual = state.runeWords.map { it.name })
    }

    @Test
    fun `표시할 항목이 없으면 빈 상태입니다`() = runTest {
        // given
        val viewModel = RankingViewModel(
            rankingRepository = RankingRepository.Fake(
                RuneRanking(periodDays = 0, startDate = "", endDate = "", runes = emptyList(), runeWords = emptyList())
            ),
            context = context
        )

        // when
        val state = viewModel.rankingState.first { it == RankingState.Empty }

        // then
        assertEquals(expected = RankingState.Empty, actual = state)
    }
}
