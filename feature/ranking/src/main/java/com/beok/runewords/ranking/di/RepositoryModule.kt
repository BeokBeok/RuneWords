package com.beok.runewords.ranking.di

import com.beok.runewords.ranking.data.RankingRepositoryImpl
import com.beok.runewords.ranking.domain.RankingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsRankingRepository(impl: RankingRepositoryImpl): RankingRepository
}
