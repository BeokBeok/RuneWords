package com.beok.runewords.combination.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.beok.runewords.combination.data.model.RuneTable
import kotlinx.coroutines.flow.Flow

@Dao
internal interface RuneDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: RuneTable)

    @Query(value = "SELECT * FROM rune WHERE :rune = name")
    fun findRuneWordsBy(rune: String): Flow<List<RuneTable>>
}
