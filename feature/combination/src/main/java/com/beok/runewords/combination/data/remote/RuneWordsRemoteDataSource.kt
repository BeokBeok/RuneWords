package com.beok.runewords.combination.data.remote

import com.beok.runewords.combination.data.entity.RuneWordsResponse

internal interface RuneWordsRemoteDataSource {

    suspend fun searchByRune(rune: String): List<RuneWordsResponse>

    class Fake : RuneWordsRemoteDataSource {
        override suspend fun searchByRune(rune: String): List<RuneWordsResponse> =
            listOf(
                RuneWordsResponse(name = "breath_of_the_dying"),
                RuneWordsResponse(name = "obsession")
            )
    }
}
