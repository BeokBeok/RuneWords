package com.beok.runewords.combination.domain

import com.beok.runewords.combination.domain.model.RuneWords

internal interface RuneWordsRepository {

    suspend fun searchByRune(rune: String): List<RuneWords>

    suspend fun fetchRuneInfoIconType(): String

    class Fake : RuneWordsRepository {

        override suspend fun searchByRune(rune: String): List<RuneWords> = listOf(
            RuneWords(name = "death_breath"),
            RuneWords(name = "patience")
        )

        override suspend fun fetchRuneInfoIconType(): String {
            return listOf("info", "more").first()
        }
    }
}
