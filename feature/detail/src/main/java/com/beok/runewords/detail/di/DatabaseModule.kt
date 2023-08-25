package com.beok.runewords.detail.di

import android.content.Context
import androidx.room.Room
import com.beok.runewords.detail.data.local.RuneWordsDetailDAO
import com.beok.runewords.detail.data.local.RuneWordsDetailDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DatabaseModule {

    @Provides
    @Singleton
    fun providesRuneWordsDetailDatabase(
        @ApplicationContext context: Context
    ): RuneWordsDetailDatabase {
        return Room
            .databaseBuilder(
                context = context,
                klass = RuneWordsDetailDatabase::class.java,
                name = "detail.db"
            )
            .build()
    }

    @Provides
    @Singleton
    fun providesRuneWordsDetailDAO(database: RuneWordsDetailDatabase): RuneWordsDetailDAO {
        return database.runeWordsDetailDAO()
    }
}
