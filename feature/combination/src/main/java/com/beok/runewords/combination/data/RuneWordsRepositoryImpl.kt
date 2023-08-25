package com.beok.runewords.combination.data

import com.beok.runewords.combination.data.local.RuneDAO
import com.beok.runewords.combination.data.model.RuneTable
import com.beok.runewords.combination.data.remote.RuneWordsRemoteDataSource
import com.beok.runewords.combination.domain.RuneWordsRepository
import com.beok.runewords.combination.domain.model.RuneWords
import com.beok.runewords.common.util.toDomain
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

internal class RuneWordsRepositoryImpl @Inject constructor(
    private val localDataSource: RuneDAO,
    private val remoteDataSource: RuneWordsRemoteDataSource
) : RuneWordsRepository {

    override fun searchByRune(rune: String): Flow<List<RuneWords>> {
        return localDataSource.findRuneWordsBy(rune)
            .flatMapConcat { local ->
                if (local.isNotEmpty()) {
                    return@flatMapConcat emitAndSync(runeTables = local, rune = rune)
                }
                emitAndSet(rune = rune)
            }
    }

    private suspend fun emitAndSet(rune: String): Flow<List<RuneWords>> {
        return flowOf(
            remoteDataSource.searchByRune(rune)
                .toDomain()
                .also {
                    localDataSource.insert(
                        RuneTable(name = rune, runewords = it.map(RuneWords::name))
                    )
                }
        )
    }

    private fun emitAndSync(
        runeTables: List<RuneTable>,
        rune: String
    ): Flow<List<RuneWords>> {
        return flow {
            runeTables.flatMap { runeTable ->
                runeTable.runewords.map(::RuneWords)
            }.also {
                emit(it)
            }.let { local ->
                remoteDataSource.searchByRune(rune)
                    .toDomain()
                    .also { remote ->
                        if (local == remote) return@let
                        localDataSource.insert(
                            RuneTable(name = rune, runewords = remote.map(RuneWords::name))
                        )
                    }
            }
        }
    }
}
