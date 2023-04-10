package com.beok.runewords.tracking.di

import com.beok.runewords.tracking.FirebaseTracker
import com.beok.runewords.tracking.Tracking
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface TrackingModule {

    @Binds
    @Singleton
    fun bindsFirebaseTracker(impl: FirebaseTracker): Tracking
}
