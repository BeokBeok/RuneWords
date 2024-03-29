package com.beok.runewords.di

import com.beok.runewords.BuildConfig
import com.beok.runewords.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.ktx.remoteConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class FirebaseModule {

    @Provides
    @Singleton
    fun providesRemoteConfig(): FirebaseRemoteConfig =
        Firebase.remoteConfig.apply {
            setDefaultsAsync(R.xml.remote_config_defaults)
        }.also {
            if (BuildConfig.DEBUG) {
                it.setConfigSettingsAsync(
                    FirebaseRemoteConfigSettings.Builder()
                        .setMinimumFetchIntervalInSeconds(0)
                        .build()
                )
            }
            it.fetchAndActivate()
        }
}
