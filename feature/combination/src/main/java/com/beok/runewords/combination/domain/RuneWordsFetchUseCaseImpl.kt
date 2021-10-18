package com.beok.runewords.combination.domain

import com.beok.runewords.combination.data.RuneWordsRepository
import com.beok.runewords.combination.domain.model.RuneWords

internal class RuneWordsFetchUseCaseImpl(
    private val repository: RuneWordsRepository
) : RuneWordsFetchUseCase {

    override suspend fun execute(rune: String): Result<List<RuneWords>> = runCatching {
        repository.searchByRune(rune)
    }
}
