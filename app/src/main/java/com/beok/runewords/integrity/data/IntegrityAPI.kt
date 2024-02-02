package com.beok.runewords.integrity.data

import com.beok.runewords.integrity.data.model.IntegrityRequest
import com.beok.runewords.integrity.data.model.IntegrityResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface IntegrityAPI {
    @POST("v1/com.beok.runewords:decodeIntegrityToken")
    suspend fun decodeToken(
        @Header("Authorization") accessToken: String,
        @Body request: IntegrityRequest
    ): IntegrityResponse
}
