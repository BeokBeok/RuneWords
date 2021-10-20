package com.beok.runewords.combination.domain

import com.beok.runewords.combination.domain.model.RuneWords

interface RuneWordsFetchUseCase {

    suspend fun execute(rune: String): Result<List<RuneWords>>

    class Fake : RuneWordsFetchUseCase {

        override suspend fun execute(rune: String): Result<List<RuneWords>> = runCatching {
            listOf(
                RuneWords(name = "death_breath"),
                RuneWords(name = "patience")
            )
        }
    }
}
