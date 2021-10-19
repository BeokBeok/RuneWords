package com.beok.runewords.combination.data.remote

import com.beok.runewords.combination.data.entity.RuneWordsResponse
import com.beok.runewords.common.ext.await
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class RuneWordsRemoteDataSourceImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : RuneWordsRemoteDataSource {

    override suspend fun searchByRune(rune: String): List<RuneWordsResponse> =
        withContext(Dispatchers.IO) {
            val runeWordGroup = mutableListOf<RuneWordsResponse>()
            fireStore
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
