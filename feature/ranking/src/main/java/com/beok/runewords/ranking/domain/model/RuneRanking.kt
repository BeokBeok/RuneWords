package com.beok.runewords.ranking.domain.model

/**
 * GA4 이벤트로 집계한 랭킹. 이름은 많이 누른 순서다.
 *
 * @property periodDays 집계 기간(일). 0이면 아직 집계 결과가 없다.
 * @property startDate 집계 시작일(yyyy-MM-dd). 비어 있으면 표시하지 않는다.
 * @property endDate 집계 종료일(yyyy-MM-dd)
 * @property runes rune_click 이벤트의 runeName 순위
 * @property runeWords rune_words_click 이벤트의 runeWordsName 순위
 */
internal data class RuneRanking(
    val periodDays: Int,
    val startDate: String,
    val endDate: String,
    val runes: List<String>,
    val runeWords: List<String>
)
