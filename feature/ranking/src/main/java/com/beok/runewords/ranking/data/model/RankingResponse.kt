package com.beok.runewords.ranking.data.model

import com.beok.runewords.common.util.DataToDomainMapper
import com.beok.runewords.ranking.domain.model.RuneRanking
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

/** Remote Config `key_ranking`의 JSON 값. GitHub Actions(update_ranking)가 게시한다. */
@Serializable
internal data class RankingResponse(
    val periodDays: Int = 0,
    val startDate: String = "",
    val endDate: String = "",
    val runes: List<String> = emptyList(),
    val runeWords: List<String> = emptyList()
) : DataToDomainMapper<RuneRanking> {

    override fun toDomain(): RuneRanking = RuneRanking(
        periodDays = periodDays,
        startDate = startDate,
        endDate = endDate,
        runes = runes,
        runeWords = runeWords
    )

    companion object {
        private val json = Json { ignoreUnknownKeys = true }

        /** 값이 비었거나 형식이 맞지 않으면 빈 랭킹으로 처리한다. */
        fun parse(value: String): RankingResponse =
            runCatching { json.decodeFromString<RankingResponse>(value) }
                .getOrDefault(RankingResponse())
    }
}
