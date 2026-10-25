package com.beok.runewords.combination.data.remote

import com.beok.runewords.combination.data.model.RuneWordsResponse
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class RuneWordsRemoteDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : RuneWordsRemoteDataSource {

    override suspend fun searchByRune(rune: String): List<RuneWordsResponse> = firestore
        .collection(COLLECTION_NAME_RUNEWORDS)
        .whereArrayContains(FIELD_NAME_RUNE_COMBINATION, rune)
        .get()
        .await()
        .toObjects<RuneWordsResponse>()

    companion object {
        private const val COLLECTION_NAME_RUNEWORDS = "runewords"
        private const val FIELD_NAME_RUNE_COMBINATION = "rune_combination"
    }
}
