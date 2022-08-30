package com.beok.runewords.home.domain

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchForceUpdateVersionUseCase @Inject constructor(
    private val repository: HomeRepository
) {

    suspend fun execute(): Result<String> = runCatching {
        repository.fetchForceUpdateVersion()
    }
}
