package com.beok.runewords.combination.domain

import com.beok.runewords.combination.data.RuneWordsRepository
import com.beok.runewords.combination.data.remote.RuneWordsRemoteDataSource
import com.beok.runewords.combination.domain.model.RuneWords
import com.beok.runewords.common.util.toDto
import javax.inject.Inject

internal class RuneWordsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RuneWordsRemoteDataSource
) : RuneWordsRepository {

    override suspend fun searchByRune(rune: String): List<RuneWords> =
        remoteDataSource
            .searchByRune(rune)
            .toDto()
}
