package com.beok.runewords.home.data.di

import com.beok.runewords.home.data.HomeRemoteConfigService
import com.beok.runewords.home.data.HomeRemoteConfigServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface HomeServiceModule {

    @Binds
    @Singleton
    fun bindsHomeRemoteConfigService(impl: HomeRemoteConfigServiceImpl): HomeRemoteConfigService
}
