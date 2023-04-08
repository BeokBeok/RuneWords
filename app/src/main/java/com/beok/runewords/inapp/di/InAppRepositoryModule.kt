package com.beok.runewords.inapp.di

import com.beok.runewords.inapp.data.InAppRepositoryImpl
import com.beok.runewords.inapp.domain.InAppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface InAppRepositoryModule {

    @Binds
    @Singleton
    fun bindsInAppRepository(impl: InAppRepositoryImpl): InAppRepository
}
