package com.beok.runewords.detail.di

import com.beok.runewords.detail.domain.RuneWordsDetailFetchUseCase
import com.beok.runewords.detail.domain.RuneWordsDetailFetchUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class UseCaseModule {

    @Binds
    abstract fun bindsRuneWordsDetailFetchUseCase(
        impl: RuneWordsDetailFetchUseCaseImpl
    ): RuneWordsDetailFetchUseCase
}
