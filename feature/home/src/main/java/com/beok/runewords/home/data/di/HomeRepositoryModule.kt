package com.beok.runewords.home.data.di

import com.beok.runewords.home.data.HomeRepositoryImpl
import com.beok.runewords.home.domain.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface HomeRepositoryModule {

    @Binds
    @Singleton
    fun bindsHomeRepository(impl: HomeRepositoryImpl): HomeRepository
}
