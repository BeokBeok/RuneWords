package com.beok.runewords.combination.data

import com.beok.runewords.combination.domain.model.RuneWords

internal interface RuneWordsRepository {

    suspend fun searchByRune(rune: String): List<RuneWords>

    class Fake : RuneWordsRepository {

        override suspend fun searchByRune(rune: String): List<RuneWords> = listOf(
            RuneWords(name = "death_breath"),
            RuneWords(name = "patience")
        )
    }
}
