package com.beok.runewords.combination.domain

internal interface RuneInfoIconTypeFetchUseCase {

    suspend fun execute(): Result<String>

    class Fake : RuneInfoIconTypeFetchUseCase {
        override suspend fun execute(): Result<String> = runCatching {
            listOf("info", "more").first()
        }
    }
}
