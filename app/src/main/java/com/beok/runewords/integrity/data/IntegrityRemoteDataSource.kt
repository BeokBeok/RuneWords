package com.beok.runewords.integrity.data

import com.beok.runewords.integrity.data.model.IntegrityResponse
import java.io.InputStream

interface IntegrityRemoteDataSource {
    suspend fun integrityToken(requestHash: String): String
    suspend fun accessToken(gcpInputStream: InputStream): String
    suspend fun decodeToken(
        accessToken: String,
        integrityToken: String
    ): IntegrityResponse
}
