package com.beok.runewords.detail.di

import com.beok.runewords.detail.data.remote.RuneWordsDetailRemoteDataSource
import com.beok.runewords.detail.data.remote.RuneWordsDetailRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {

    @Binds
    abstract fun bindsRuneWordsDetailRemoteDataSource(
        impl: RuneWordsDetailRemoteDataSourceImpl
    ): RuneWordsDetailRemoteDataSource
}
