package com.beok.runewords.detail.data.remote

import com.beok.runewords.detail.data.entity.RuneWordsDetailResponse

internal interface RuneWordsDetailRemoteDataSource {

    suspend fun fetchInfo(name: String): RuneWordsDetailResponse

    class Fake : RuneWordsDetailRemoteDataSource {

        override suspend fun fetchInfo(name: String): RuneWordsDetailResponse =
            RuneWordsDetailResponse(
                name = "call_to_arms",
                rune_combination = listOf("amn", "ral", "mal", "ist", "ohm"),
                type = listOf("all_weapons"),
                option = "rune_word_call_to_arms_option"
            )
    }
}
