package com.beok.runewords.detail.data

import com.beok.runewords.detail.data.remote.RuneWordsDetailRemoteDataSource
import com.beok.runewords.detail.domain.RuneWordsDetailRepository
import com.beok.runewords.detail.domain.model.RuneWordsDetail
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class RuneWordsDetailRepositoryImpl @Inject constructor(
    private val remoteDataSource: RuneWordsDetailRemoteDataSource
) : RuneWordsDetailRepository {
    private val cache = mutableMapOf<String, RuneWordsDetail>()

    override fun fetchInfo(name: String): Flow<RuneWordsDetail> {
        return flow {
            cache.getOrPut(
                key = name,
                defaultValue = {
                    remoteDataSource.fetchInfo(name).toDomain()
                }
            ).also { local ->
                emit(local)
            }.let { local ->
                remoteDataSource.fetchInfo(name)
                    .toDomain()
                    .let { remote ->
                        if (local == remote) return@flow
                        cache[name] = remote
                    }
            }
        }
    }
}
