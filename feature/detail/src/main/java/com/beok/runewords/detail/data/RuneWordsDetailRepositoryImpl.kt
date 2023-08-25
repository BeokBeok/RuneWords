package com.beok.runewords.detail.data

import com.beok.runewords.common.util.toDomain
import com.beok.runewords.detail.data.local.RuneWordsDetailDAO
import com.beok.runewords.detail.data.model.RuneWordsDetailTable
import com.beok.runewords.detail.data.remote.RuneWordsDetailRemoteDataSource
import com.beok.runewords.detail.domain.RuneWordsDetailRepository
import com.beok.runewords.detail.domain.model.RuneWordsDetail
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow

internal class RuneWordsDetailRepositoryImpl @Inject constructor(
    private val localDataSource: RuneWordsDetailDAO,
    private val remoteDataSource: RuneWordsDetailRemoteDataSource
) : RuneWordsDetailRepository {

    override fun fetchInfo(name: String): Flow<RuneWordsDetail> {
        return localDataSource.findBy(name)
            .flatMapConcat {
                if (it.isNotEmpty()) {
                    return@flatMapConcat it.toDomain().asFlow()
                }
                flow {
                    emit(
                        remoteDataSource.fetchInfo(name)
                            .toDomain()
                            .also { runeWordsDetail ->
                                localDataSource.insert(
                                    RuneWordsDetailTable.fromDomain(runeWordsDetail)
                                )
                            }
                    )
                }
            }
    }
}
