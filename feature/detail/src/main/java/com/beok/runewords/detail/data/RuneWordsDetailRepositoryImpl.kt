package com.beok.runewords.detail.data

import com.beok.runewords.common.util.toDomain
import com.beok.runewords.detail.data.local.RuneWordsDetailDAO
import com.beok.runewords.detail.data.model.RuneWordsDetailTable
import com.beok.runewords.detail.data.remote.RuneWordsDetailRemoteDataSource
import com.beok.runewords.detail.domain.RuneWordsDetailRepository
import com.beok.runewords.detail.domain.model.RuneWordsDetail
import javax.inject.Inject
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

    private suspend fun emitAndSet(runeName: String): Flow<RuneWordsDetail> {
        return flowOf(
            remoteDataSource.fetchInfo(runeName)
                .toDomain()
                .also { runeWordsDetail ->
                    localDataSource.insert(
                        RuneWordsDetailTable.fromDomain(runeWordsDetail)
                    )
                }
        )
    }

    private fun emitAndSync(
        runeWordsDetailTables: List<RuneWordsDetailTable>,
        runeName: String
    ): Flow<RuneWordsDetail> {
        return flow {
            runeWordsDetailTables.toDomain()
                .first()
                .also {
                    emit(it)
                }.let { local ->
                    remoteDataSource.fetchInfo(runeName)
                        .toDomain()
                        .also { remote ->
                            if (local == remote) return@let
                            localDataSource.insert(RuneWordsDetailTable.fromDomain(remote))
                        }
                }
        }
    }
}
