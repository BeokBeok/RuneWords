package com.beok.runewords.combination.data.remote

import com.beok.runewords.combination.data.entity.RuneWordsResponse
import com.beok.runewords.common.ext.await
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class RuneWordsRemoteDataSourceImpl @Inject constructor(
    private val fireStore: FirebaseFirestore,
    private val remoteConfig: FirebaseRemoteConfig
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

    override suspend fun fetchRuneInfoIconType(): String = withContext(Dispatchers.IO) {
        remoteConfig.fetchAndActivate()
            .await()
            .run {
                remoteConfig.getString(KEY_RUNE_INFO_ICON_TYPE)
            }
    }

    companion object {
        private const val COLLECTION_NAME_RUNEWORDS = "runewords"
        private const val FIELD_NAME_RUNE_COMBINATION = "rune_combination"
        private const val KEY_RUNE_INFO_ICON_TYPE = "key_rune_info_icon_type"
    }
}
