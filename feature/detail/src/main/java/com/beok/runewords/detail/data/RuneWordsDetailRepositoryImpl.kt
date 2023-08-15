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

    override fun fetchInfo(name: String): Flow<RuneWordsDetail> {
        return flow {
            emit(remoteDataSource.fetchInfo(name).toDomain())
        }
    }
}
