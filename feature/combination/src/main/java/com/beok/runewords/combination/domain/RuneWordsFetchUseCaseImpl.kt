package com.beok.runewords.combination.domain

import com.beok.runewords.combination.domain.model.RuneWords
import javax.inject.Inject

internal class RuneWordsFetchUseCaseImpl @Inject constructor(
    private val repository: RuneWordsRepository
) : RuneWordsFetchUseCase {

    override suspend fun execute(rune: String): Result<List<RuneWords>> = runCatching {
        repository.searchByRune(rune)
    }
}
