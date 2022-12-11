package com.beok.runewords.combination.domain

import javax.inject.Inject

internal class RuneInfoIconTypeFetchUseCaseImpl @Inject constructor(
    private val repository: RuneWordsRepository
) : RuneInfoIconTypeFetchUseCase {

    override suspend fun execute(): Result<String> = runCatching {
        repository.fetchRuneInfoIconType()
    }
}
