package com.beok.runewords.integrity.domain

import com.beok.runewords.integrity.domain.model.AppRecognitionVerdict
import java.io.InputStream

interface IntegrityRepository {
    suspend fun integrity(
        requestHash: String,
        gcpInputStream: InputStream
    ): Result<AppRecognitionVerdict>
}
