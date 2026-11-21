package com.beok.runewords.ranking.data

import com.beok.runewords.ranking.data.model.RankingResponse
import com.beok.runewords.ranking.domain.RankingRepository
import com.beok.runewords.ranking.domain.model.RuneRanking
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import javax.inject.Inject
import kotlin.coroutines.coroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeoutOrNull

internal class RankingRepositoryImpl @Inject constructor(
    private val remoteConfig: FirebaseRemoteConfig
) : RankingRepository {

    override fun fetchRanking(): Flow<RuneRanking> = flow {
        // 갱신에 실패해도 마지막으로 활성화한 값(없으면 기본값)을 표시합니다.
        runCatching {
            withTimeoutOrNull(FETCH_TIMEOUT_MS) { remoteConfig.fetchAndActivate().await() }
        }.onFailure { coroutineContext.ensureActive() }
        emit(RankingResponse.parse(remoteConfig.getString(KEY_RANKING)).toDomain())
    }

    private companion object {
        private const val KEY_RANKING = "key_ranking"
        private const val FETCH_TIMEOUT_MS = 3_000L
    }
}
