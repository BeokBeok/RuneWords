package com.beok.runewords.detail.data

import com.beok.runewords.detail.data.remote.RuneWordsDetailRemoteDataSource
import com.beok.runewords.detail.domain.RuneWordsDetailRepository
import com.beok.runewords.detail.domain.model.RuneWordsDetail
import javax.inject.Inject

internal class RuneWordsDetailRepositoryImpl @Inject constructor(
    private val remoteDataSource: RuneWordsDetailRemoteDataSource
) : RuneWordsDetailRepository {

    override suspend fun fetchInfo(name: String): RuneWordsDetail = remoteDataSource
        .fetchInfo(name)
        .toDto()
}
