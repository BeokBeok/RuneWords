package com.beok.runewords.combination.di

import com.google.firebase.firestore.FirebaseFirestore
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
    fun providesFireStore(): FirebaseFirestore = FirebaseFirestore.getInstance()
}
