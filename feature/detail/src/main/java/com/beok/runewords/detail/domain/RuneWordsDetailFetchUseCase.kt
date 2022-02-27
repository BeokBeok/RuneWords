package com.beok.runewords.detail.domain

import com.beok.runewords.detail.domain.model.RuneWordsDetail

internal interface RuneWordsDetailFetchUseCase {

    suspend fun execute(name: String): Result<RuneWordsDetail>

    class Fake : RuneWordsDetailFetchUseCase {

        override suspend fun execute(name: String): Result<RuneWordsDetail> = runCatching {
            RuneWordsDetail(
                name = "call_to_arms",
                runeCombination = listOf("amn", "ral", "mal", "ist", "ohm"),
                type = listOf("all_weapons"),
                option = "rune_word_call_to_arms_option"
            )
        }
    }
}
