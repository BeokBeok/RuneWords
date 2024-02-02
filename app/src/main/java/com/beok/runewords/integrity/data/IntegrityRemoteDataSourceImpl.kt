package com.beok.runewords.integrity.data

import com.beok.runewords.common.ext.await
import com.beok.runewords.integrity.data.model.IntegrityRequest
import com.beok.runewords.integrity.data.model.IntegrityResponse
import com.google.android.play.core.integrity.StandardIntegrityManager
import com.google.android.play.core.integrity.StandardIntegrityManager.StandardIntegrityTokenRequest
import com.google.auth.oauth2.GoogleCredentials
import com.google.auth.oauth2.ServiceAccountCredentials
import java.io.InputStream
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class IntegrityRemoteDataSourceImpl @Inject constructor(
    private val standardIntegrityManager: StandardIntegrityManager,
    private val integrityAPI: IntegrityAPI
) : IntegrityRemoteDataSource {
    override suspend fun integrityToken(requestHash: String): String {
        return withContext(Dispatchers.IO) {
            standardIntegrityManager.prepareIntegrityToken(
                StandardIntegrityManager.PrepareIntegrityTokenRequest
                    .builder()
                    .setCloudProjectNumber(CLOUD_PROJECT_NUMBER)
                    .build()
            )
                .await()
                .request(
                    StandardIntegrityTokenRequest.builder()
                        .setRequestHash(requestHash)
                        .build()
                )
                .await()
                .token()
        }
    }

    override suspend fun accessToken(gcpInputStream: InputStream): String {
        return withContext(Dispatchers.IO) {
            gcpInputStream.use {
                val credentials = GoogleCredentials.fromStream(it)
                    .createScoped(listOf(INTEGRITY_SCOPE))

                credentials.refreshIfExpired()

                (credentials as? ServiceAccountCredentials)?.accessToken
                    ?.tokenValue
                    .orEmpty()
            }
        }
    }

    override suspend fun decodeToken(
        accessToken: String,
        integrityToken: String
    ): IntegrityResponse {
        return integrityAPI.decodeToken(
            accessToken = "Bearer $accessToken",
            request = IntegrityRequest(token = integrityToken)
        )
    }

    companion object {
        private const val CLOUD_PROJECT_NUMBER: Long = 518630916368
        private const val INTEGRITY_SCOPE: String = "https://www.googleapis.com/auth/playintegrity"
    }
}
