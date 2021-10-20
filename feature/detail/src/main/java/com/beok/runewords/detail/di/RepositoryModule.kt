package com.beok.runewords.detail.di

import com.beok.runewords.detail.data.RuneWordsDetailRepositoryImpl
import com.beok.runewords.detail.domain.RuneWordsDetailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindsRuneWordsDetailRepository(
        impl: RuneWordsDetailRepositoryImpl
    ): RuneWordsDetailRepository
}
