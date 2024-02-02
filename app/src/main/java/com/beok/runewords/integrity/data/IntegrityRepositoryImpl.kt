package com.beok.runewords.integrity.data

import com.beok.runewords.integrity.data.model.AppIntegrity
import com.beok.runewords.integrity.domain.IntegrityRepository
import java.io.InputStream
import javax.inject.Inject

class IntegrityRepositoryImpl @Inject constructor(
    private val integrityRemoteDataSource: IntegrityRemoteDataSource
) : IntegrityRepository {
    override suspend fun integrity(
        requestHash: String,
        gcpInputStream: InputStream
    ): Result<Boolean> {
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
            val isRecognize =
                AppIntegrity.AppRecognitionVerdict
                    .isRecognize(
                        appRecognitionVerdict = response.tokenPayloadExternal
                            .appIntegrity
                            .appRecognitionVerdict
                    )
            val isEqualsHash =
                response.tokenPayloadExternal.requestDetails.requestHash == requestHash
            isRecognize && isEqualsHash
        }
    }
}
