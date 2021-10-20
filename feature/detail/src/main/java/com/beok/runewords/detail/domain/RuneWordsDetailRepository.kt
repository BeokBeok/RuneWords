package com.beok.runewords.detail.domain

import com.beok.runewords.detail.domain.model.RuneWordsDetail

internal interface RuneWordsDetailRepository {

    suspend fun fetchInfo(name: String): RuneWordsDetail

    class Fake : RuneWordsDetailRepository {

        override suspend fun fetchInfo(name: String): RuneWordsDetail = RuneWordsDetail(
            name = "call_to_arms",
            runeCombination = listOf("amn", "ral", "mal", "ist", "ohm"),
            type = listOf("all_weapons")
        )
    }
}
