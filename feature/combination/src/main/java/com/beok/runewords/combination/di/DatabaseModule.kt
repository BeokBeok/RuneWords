package com.beok.runewords.combination.di

import android.content.Context
import androidx.room.Room
import com.beok.runewords.combination.data.local.RuneDAO
import com.beok.runewords.combination.data.local.RuneDatabase
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
    fun providesRuneDatabase(@ApplicationContext context: Context): RuneDatabase {
        return Room
            .databaseBuilder(
                context = context,
                klass = RuneDatabase::class.java,
                name = "rune.db"
            )
            .build()
    }

    @Provides
    @Singleton
    fun providesRuneDAO(database: RuneDatabase): RuneDAO {
        return database.runeDAO()
    }
}
