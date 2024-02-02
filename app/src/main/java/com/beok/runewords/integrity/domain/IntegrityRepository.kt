package com.beok.runewords.integrity.domain

import java.io.InputStream

interface IntegrityRepository {
    suspend fun integrity(
        requestHash: String,
        gcpInputStream: InputStream
    ): Result<Boolean>
}
