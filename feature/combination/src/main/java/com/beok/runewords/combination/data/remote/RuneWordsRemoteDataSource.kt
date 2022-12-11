package com.beok.runewords.combination.data.remote

import com.beok.runewords.combination.data.entity.RuneWordsResponse

internal interface RuneWordsRemoteDataSource {

    suspend fun searchByRune(rune: String): List<RuneWordsResponse>

    suspend fun fetchRuneInfoIconType(): String

    class Fake : RuneWordsRemoteDataSource {
        override suspend fun searchByRune(rune: String): List<RuneWordsResponse> =
            listOf(
                RuneWordsResponse(name = "death_breath"),
                RuneWordsResponse(name = "patience")
            )

        override suspend fun fetchRuneInfoIconType(): String {
            return listOf("info", "more").first()
        }
    }
}
