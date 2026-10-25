package com.beok.runewords.combination.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.beok.runewords.combination.data.model.RuneTable

@Database(
    entities = [RuneTable::class],
    version = 2
)
@TypeConverters(RuneWordsCacheListTypeConverter::class)
internal abstract class RuneDatabase : RoomDatabase() {
    abstract fun runeDAO(): RuneDAO

    companion object {
        // v1 캐시는 룬워드 이름만 문자열 배열로 저장해 새 형식으로 읽을 수 없으므로 비웁니다.
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("DELETE FROM rune")
            }
        }
    }
}
