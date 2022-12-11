package com.beok.runewords.combination.di

import com.beok.runewords.combination.domain.RuneInfoIconTypeFetchUseCase
import com.beok.runewords.combination.domain.RuneInfoIconTypeFetchUseCaseImpl
import com.beok.runewords.combination.domain.RuneWordsFetchUseCase
import com.beok.runewords.combination.domain.RuneWordsFetchUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindsRuneWordsFetchUseCase(impl: RuneWordsFetchUseCaseImpl): RuneWordsFetchUseCase

    @Binds
    @Singleton
    abstract fun bindsRuneInfoIconTypeFetchUseCase(
        impl: RuneInfoIconTypeFetchUseCaseImpl
    ): RuneInfoIconTypeFetchUseCase
}
