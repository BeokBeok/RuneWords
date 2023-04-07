package com.beok.runewords.combination.data

import com.beok.runewords.combination.data.remote.RuneWordsRemoteDataSource
import com.beok.runewords.combination.domain.RuneWordsRepository
import com.beok.runewords.combination.domain.model.RuneWords
import com.beok.runewords.common.util.toDto
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class RuneWordsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RuneWordsRemoteDataSource
) : RuneWordsRepository {

    override fun searchByRune(rune: String): Flow<List<RuneWords>> {
        return flow {
            emit(remoteDataSource.searchByRune(rune).toDto())
        }
    }
}
