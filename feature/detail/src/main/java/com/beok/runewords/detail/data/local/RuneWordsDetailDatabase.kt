package com.beok.runewords.detail.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.beok.runewords.detail.data.model.RuneWordsDetailTable

@Database(
    entities = [RuneWordsDetailTable::class],
    version = 1
)
@TypeConverters(StringListTypeConverter::class)
internal abstract class RuneWordsDetailDatabase : RoomDatabase() {
    abstract fun runeWordsDetailDAO(): RuneWordsDetailDAO
}
