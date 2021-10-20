package com.beok.runewords.detail.domain

import com.beok.runewords.detail.domain.model.RuneWordsDetail
import javax.inject.Inject

internal class RuneWordsDetailFetchUseCaseImpl @Inject constructor(
    private val repository: RuneWordsDetailRepository
) : RuneWordsDetailFetchUseCase {

    override suspend fun execute(name: String): Result<RuneWordsDetail> = runCatching {
        repository.fetchInfo(name)
    }
}
