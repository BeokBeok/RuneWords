package com.beok.runewords.combination.di

import com.beok.runewords.combination.domain.RuneWordsRepository
import com.beok.runewords.combination.data.RuneWordsRepositoryImpl
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
    abstract fun bindsRuneWordsRepository(impl: RuneWordsRepositoryImpl): RuneWordsRepository
}
