package com.beok.runewords.combination.domain

import com.beok.runewords.combination.domain.model.RuneWords

internal interface RuneWordsFetchUseCase {

    suspend fun execute(rune: String): Result<List<RuneWords>>

    class Fake : RuneWordsFetchUseCase {

        override suspend fun execute(rune: String): Result<List<RuneWords>> = runCatching {
            listOf(
                RuneWords(
                    name = "death_breath",
                    runeCombination = listOf("vex", "hel", "el", "eld", "zod", "eth")
                ),
                RuneWords(
                    name = "patience",
                    runeCombination = listOf("el", "sol", "dol", "lo")
                )
            )
        }
    }
}
