package com.beok.runewords.inapp.di

import com.beok.runewords.inapp.data.InAppRemoteConfigService
import com.beok.runewords.inapp.data.InAppRemoteConfigServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface InAppServiceModule {

    @Binds
    @Singleton
    fun bindsInAppRemoteConfigService(impl: InAppRemoteConfigServiceImpl): InAppRemoteConfigService
}
