package com.beok.runewords.home.data

import com.beok.runewords.home.domain.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val remoteConfigService: HomeRemoteConfigService
) : HomeRepository {

    override suspend fun fetchForceUpdateVersion(): String {
        return remoteConfigService.fetchForceUpdateVersion()
    }
}
