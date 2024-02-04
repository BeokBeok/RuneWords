package com.beok.runewords.integrity.data

import com.beok.runewords.integrity.domain.IntegrityRepository
import com.beok.runewords.integrity.domain.model.AppRecognitionVerdict
import java.io.InputStream
import javax.inject.Inject

class IntegrityRepositoryImpl @Inject constructor(
    private val integrityRemoteDataSource: IntegrityRemoteDataSource
) : IntegrityRepository {
    override suspend fun integrity(
        requestHash: String,
        gcpInputStream: InputStream
    ): Result<AppRecognitionVerdict> {
        return runCatching {
            val integrityToken = integrityRemoteDataSource.integrityToken(
                requestHash = requestHash
            )
            val accessToken = integrityRemoteDataSource.accessToken(
                gcpInputStream = gcpInputStream
            )
            val response = integrityRemoteDataSource.decodeToken(
                accessToken = accessToken,
                integrityToken = integrityToken
            )
            if (response.tokenPayloadExternal.requestDetails.requestHash != requestHash) {
                AppRecognitionVerdict.NOT_MATCH_HASH
            } else {
                AppRecognitionVerdict.NONE
            }
        }
    }
}
