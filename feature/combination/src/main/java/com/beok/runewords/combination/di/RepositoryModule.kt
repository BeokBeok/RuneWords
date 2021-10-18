package com.beok.runewords.combination.di

import com.beok.runewords.combination.data.RuneWordsRepository
import com.beok.runewords.combination.domain.RuneWordsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindsRuneWordsRepository(impl: RuneWordsRepositoryImpl): RuneWordsRepository
}
