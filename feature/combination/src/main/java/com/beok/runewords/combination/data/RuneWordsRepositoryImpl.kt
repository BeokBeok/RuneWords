package com.beok.runewords.combination.data

import com.beok.runewords.combination.data.local.RuneDAO
import com.beok.runewords.combination.data.model.RuneTable
import com.beok.runewords.combination.data.model.RuneWordsCache
import com.beok.runewords.combination.data.remote.RuneWordsRemoteDataSource
import com.beok.runewords.combination.domain.RuneWordsRepository
import com.beok.runewords.combination.domain.model.RuneWords
import com.beok.runewords.common.util.toDomain
import javax.inject.Inject
import kotlin.coroutines.coroutineContext
import kotlinx.coroutines.ensureActive
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

    private suspend fun emitAndSet(rune: String): Flow<List<RuneWords>> = flowOf(
        remoteDataSource.searchByRune(rune)
            .toDomain()
            .also {
                localDataSource.insert(
                    RuneTable(name = rune, runewords = it.map(RuneWordsCache::from))
                )
            }
    )

    private fun emitAndSync(runeTables: List<RuneTable>, rune: String): Flow<List<RuneWords>> {
        return flow {
            val local = runeTables.flatMap { runeTable -> runeTable.runewords.toDomain() }
            emit(local)
            // 동기화에 실패해도 이미 표시한 캐시를 유지합니다.
            val remote = runCatching { remoteDataSource.searchByRune(rune).toDomain() }.getOrElse {
                coroutineContext.ensureActive()
                return@flow
            }
            if (local == remote) return@flow
            localDataSource.insert(
                RuneTable(name = rune, runewords = remote.map(RuneWordsCache::from))
            )
        }
    }
}
