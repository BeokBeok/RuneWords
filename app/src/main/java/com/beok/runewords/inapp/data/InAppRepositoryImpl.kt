package com.beok.runewords.inapp.data

import com.beok.runewords.inapp.domain.InAppRepository
import javax.inject.Inject

class InAppRepositoryImpl @Inject constructor(
    private val remoteConfigService: InAppRemoteConfigService
) : InAppRepository {

    override suspend fun fetchForceUpdateVersion(): Result<String> {
        return runCatching {
            remoteConfigService.fetchForceUpdateVersion()
        }
    }
}
