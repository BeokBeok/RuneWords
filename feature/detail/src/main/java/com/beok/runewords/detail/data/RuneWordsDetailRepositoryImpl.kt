package com.beok.runewords.detail.data

import com.beok.runewords.common.util.toDomain
import com.beok.runewords.detail.data.local.RuneWordsDetailDAO
import com.beok.runewords.detail.data.model.RuneWordsDetailTable
import com.beok.runewords.detail.data.remote.RuneWordsDetailRemoteDataSource
import com.beok.runewords.detail.domain.RuneWordsDetailRepository
import com.beok.runewords.detail.domain.model.RuneWordsDetail
import javax.inject.Inject
import kotlin.coroutines.coroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

internal class RuneWordsDetailRepositoryImpl @Inject constructor(
    private val localDataSource: RuneWordsDetailDAO,
    private val remoteDataSource: RuneWordsDetailRemoteDataSource
) : RuneWordsDetailRepository {

    override fun fetchInfo(name: String): Flow<RuneWordsDetail> {
        return localDataSource.findBy(name)
            .flatMapConcat { local ->
                if (local.isNotEmpty()) {
                    return@flatMapConcat emitAndSync(runeWordsDetailTables = local, runeName = name)
                }
                emitAndSet(runeName = name)
            }
    }

    private suspend fun emitAndSet(runeName: String): Flow<RuneWordsDetail> = flowOf(
        fetchRemote(runeName)
            .also { runeWordsDetail ->
                localDataSource.insert(
                    RuneWordsDetailTable.fromDomain(runeWordsDetail)
                )
            }
    )

    private fun emitAndSync(
        runeWordsDetailTables: List<RuneWordsDetailTable>,
        runeName: String
    ): Flow<RuneWordsDetail> {
        return flow {
            val local = runeWordsDetailTables.toDomain().first()
            emit(local)
            // 동기화에 실패해도 이미 표시한 캐시를 유지합니다.
            val remote = runCatching { fetchRemote(runeName) }.getOrElse {
                coroutineContext.ensureActive()
                return@flow
            }
            if (local == remote) return@flow
            localDataSource.insert(RuneWordsDetailTable.fromDomain(remote))
        }
    }

    // 조회 키와 다른 이름을 저장하면 Room Flow가 다시 발행되어 원격 요청이 끝없이 반복됩니다.
    private suspend fun fetchRemote(runeName: String): RuneWordsDetail =
        remoteDataSource.fetchInfo(runeName)
            .toDomain()
            .also { remote ->
                check(remote.name == runeName) { "문서를 찾을 수 없습니다: $runeName" }
            }
}
