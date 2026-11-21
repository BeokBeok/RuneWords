package com.beok.runewords.ranking.data

import com.beok.runewords.ranking.domain.model.RuneRanking
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

internal class RankingResponseTest {

    @Test
    fun `Remote Config JSON을 랭킹으로 바꿉니다`() {
        // given
        val value = """{"periodDays":7,"runes":["ber","jah"],"runeWords":["enigma"],"unknown":1}"""

        // when
        val actual = RankingResponse.parse(value).toDomain()

        // then
        assertEquals(
            expected = RuneRanking(periodDays = 7, runes = listOf("ber", "jah"), runeWords = listOf("enigma")),
            actual = actual
        )
    }

    @Test
    fun `기본값이나 잘못된 JSON이면 빈 랭킹을 돌려줍니다`() {
        val empty = RuneRanking(periodDays = 0, runes = emptyList(), runeWords = emptyList())

        assertEquals(expected = empty, actual = RankingResponse.parse("{}").toDomain())
        assertEquals(expected = empty, actual = RankingResponse.parse("").toDomain())
        assertEquals(expected = empty, actual = RankingResponse.parse("not json").toDomain())
    }
}
