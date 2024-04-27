package com.beok.runewords.integrity.di

import android.content.Context
import com.beok.runewords.integrity.data.IntegrityAPI
import com.beok.runewords.integrity.data.IntegrityRemoteDataSource
import com.beok.runewords.integrity.data.IntegrityRemoteDataSourceImpl
import com.beok.runewords.integrity.data.IntegrityRepositoryImpl
import com.beok.runewords.integrity.domain.IntegrityRepository
import com.google.android.play.core.integrity.IntegrityManagerFactory
import com.google.android.play.core.integrity.StandardIntegrityManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
interface IntegrityModule {
    @Binds
    @Singleton
    fun bindsIntegrityRemoteService(impl: IntegrityRemoteDataSourceImpl): IntegrityRemoteDataSource

    @Binds
    @Singleton
    fun bindsIntegrityRepository(impl: IntegrityRepositoryImpl): IntegrityRepository

    companion object {
        @Provides
        @Singleton
        fun providesStandardIntegrityManager(
            @ApplicationContext context: Context
        ): StandardIntegrityManager {
            return IntegrityManagerFactory.createStandard(context)
        }

        @Provides
        @Singleton
        fun providesIntegrityAPI(): IntegrityAPI {
            val json = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }
            return Retrofit.Builder()
                .baseUrl("https://playintegrity.googleapis.com")
                .addConverterFactory(
                    json.asConverterFactory(contentType = "application/json".toMediaType())
                )
                .build()
                .create(IntegrityAPI::class.java)
        }
    }
}
