package com.beok.runewords.detail.data.remote

import com.beok.runewords.common.ext.await
import com.beok.runewords.detail.data.entity.RuneWordsDetailResponse
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class RuneWordsDetailRemoteDataSourceImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : RuneWordsDetailRemoteDataSource {

    override suspend fun fetchInfo(name: String): RuneWordsDetailResponse =
        withContext(Dispatchers.IO) {
            fireStore
                .collection(COLLECTION_NAME_RUNEWORDS)
                .document(name)
                .get()
                .await()
                .toObject(RuneWordsDetailResponse::class.java)
                ?: RuneWordsDetailResponse()
        }

    companion object {
        private const val COLLECTION_NAME_RUNEWORDS = "runewords"
    }
}
