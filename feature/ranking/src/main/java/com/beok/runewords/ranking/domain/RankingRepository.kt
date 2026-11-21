package com.beok.runewords.ranking.domain

import com.beok.runewords.ranking.domain.model.RuneRanking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal interface RankingRepository {

    fun fetchRanking(): Flow<RuneRanking>

    class Fake(private val ranking: RuneRanking) : RankingRepository {

        override fun fetchRanking(): Flow<RuneRanking> = flowOf(ranking)
    }
}
