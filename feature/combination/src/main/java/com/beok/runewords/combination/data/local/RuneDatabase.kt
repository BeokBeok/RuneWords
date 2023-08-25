package com.beok.runewords.combination.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.beok.runewords.combination.data.model.RuneTable

@Database(
    entities = [RuneTable::class],
    version = 1
)
@TypeConverters(StringListTypeConverter::class)
internal abstract class RuneDatabase : RoomDatabase() {
    abstract fun runeDAO(): RuneDAO
}
