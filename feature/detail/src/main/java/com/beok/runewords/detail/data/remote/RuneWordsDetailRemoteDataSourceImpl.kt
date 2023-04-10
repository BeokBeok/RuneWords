package com.beok.runewords.detail.data.remote

import com.beok.runewords.common.ext.await
import com.beok.runewords.detail.data.entity.RuneWordsDetailResponse
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class RuneWordsDetailRemoteDataSourceImpl @Inject constructor() :
    RuneWordsDetailRemoteDataSource {

    override suspend fun fetchInfo(name: String): RuneWordsDetailResponse =
        withContext(Dispatchers.IO) {
            Firebase.firestore
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
