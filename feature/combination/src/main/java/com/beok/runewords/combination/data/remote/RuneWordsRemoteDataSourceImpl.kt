package com.beok.runewords.combination.data.remote

import com.beok.runewords.combination.data.model.RuneWordsResponse
import com.beok.runewords.common.ext.await
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class RuneWordsRemoteDataSourceImpl @Inject constructor() : RuneWordsRemoteDataSource {

    override suspend fun searchByRune(rune: String): List<RuneWordsResponse> =
        withContext(Dispatchers.IO) {
            val runeWordGroup = mutableListOf<RuneWordsResponse>()
            Firebase.firestore
                .collection(COLLECTION_NAME_RUNEWORDS)
                .whereArrayContains(FIELD_NAME_RUNE_COMBINATION, rune)
                .get()
                .await()
                .forEach {
                    runeWordGroup.add(it.toObject(RuneWordsResponse::class.java))
                }
            return@withContext runeWordGroup
        }

    companion object {
        private const val COLLECTION_NAME_RUNEWORDS = "runewords"
        private const val FIELD_NAME_RUNE_COMBINATION = "rune_combination"
    }
}
