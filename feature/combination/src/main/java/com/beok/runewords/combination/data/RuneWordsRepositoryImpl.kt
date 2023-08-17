package com.beok.runewords.combination.data

import com.beok.runewords.combination.data.remote.RuneWordsRemoteDataSource
import com.beok.runewords.combination.domain.RuneWordsRepository
import com.beok.runewords.combination.domain.model.RuneWords
import com.beok.runewords.common.util.toDomain
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class RuneWordsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RuneWordsRemoteDataSource
) : RuneWordsRepository {
    private val cache = mutableMapOf<String, List<RuneWords>>()

    override fun searchByRune(rune: String): Flow<List<RuneWords>> {
        return flow {
            cache.getOrPut(
                key = rune,
                defaultValue = {
                    remoteDataSource.searchByRune(rune).toDomain()
                }
            ).also { local ->
                emit(local)
            }.let { local ->
                remoteDataSource.searchByRune(rune)
                    .toDomain()
                    .let { remote ->
                        if (local == remote) return@flow
                        cache[rune] = remote
                    }
            }
        }
    }
}
