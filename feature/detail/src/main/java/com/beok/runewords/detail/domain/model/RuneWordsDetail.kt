package com.beok.runewords.detail.domain.model

internal data class RuneWordsDetail(
    val name: String,
    val runeCombination: List<String>,
    val type: List<String>,
    val option: String,
    val levelLimit: Int
)
