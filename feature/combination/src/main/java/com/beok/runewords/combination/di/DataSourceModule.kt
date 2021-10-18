package com.beok.runewords.combination.di

import com.beok.runewords.combination.data.remote.RuneWordsRemoteDataSource
import com.beok.runewords.combination.data.remote.RuneWordsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {

    @Binds
    abstract fun bindsRuneWordsRemoteDataSource(
        impl: RuneWordsRemoteDataSourceImpl
    ): RuneWordsRemoteDataSource
}
