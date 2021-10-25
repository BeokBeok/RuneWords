package com.beok.runewords.di

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class FirebaseModule {

    @Provides
    @Singleton
    fun providesFireStore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun providesFirebaseAnalytics(
        @ApplicationContext context: Context
    ): FirebaseAnalytics = FirebaseAnalytics.getInstance(context)
}
