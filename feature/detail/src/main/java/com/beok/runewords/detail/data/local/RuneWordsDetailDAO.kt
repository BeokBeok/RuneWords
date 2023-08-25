package com.beok.runewords.detail.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.beok.runewords.detail.data.model.RuneWordsDetailTable
import kotlinx.coroutines.flow.Flow

@Dao
internal interface RuneWordsDetailDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: RuneWordsDetailTable)

    @Query(value = "SELECT * FROM detail WHERE :name = name")
    fun findBy(name: String): Flow<List<RuneWordsDetailTable>>
}
