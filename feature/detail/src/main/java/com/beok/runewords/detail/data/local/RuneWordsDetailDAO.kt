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

    /** 예약어와 겹쳐 이름 끝에 "_"를 붙인 룬워드(void_)도 "_" 없는 이름으로 찾는다. */
    @Query(value = "SELECT * FROM detail WHERE name = :name OR name = :name || '_'")
    fun findBy(name: String): Flow<List<RuneWordsDetailTable>>
}
