package com.beok.runewords.detail.data.remote

import com.beok.runewords.detail.data.model.RuneWordsDetailResponse
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class RuneWordsDetailRemoteDataSourceImpl @Inject constructor() :
    RuneWordsDetailRemoteDataSource {

    override suspend fun fetchInfo(name: String): RuneWordsDetailResponse = Firebase.firestore
        .collection(COLLECTION_NAME_RUNEWORDS)
        .document(name)
        .get()
        .await()
        .toObject<RuneWordsDetailResponse>()
        ?: RuneWordsDetailResponse()

    companion object {
        private const val COLLECTION_NAME_RUNEWORDS = "runewords"
    }
}
