package com.beok.runewords.detail.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.beok.runewords.common.util.DataToDomainMapper
import com.beok.runewords.detail.domain.model.RuneWordsDetail

@Entity(tableName = "detail")
internal data class RuneWordsDetailTable constructor(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "runeCombination")
    val runeCombination: List<String>,
    @ColumnInfo(name = "type")
    val type: List<String>,
    @ColumnInfo(name = "option")
    val option: String,
    @ColumnInfo(name = "levelLimit")
    val levelLimit: Int
) : DataToDomainMapper<RuneWordsDetail> {
    override fun toDomain(): RuneWordsDetail {
        return RuneWordsDetail(
            name = name,
            runeCombination = runeCombination,
            type = type,
            option = option,
            levelLimit = levelLimit
        )
    }

    companion object {
        fun fromDomain(runeWordsDetail: RuneWordsDetail): RuneWordsDetailTable {
            return RuneWordsDetailTable(
                name = runeWordsDetail.name,
                runeCombination = runeWordsDetail.runeCombination,
                type = runeWordsDetail.type,
                option = runeWordsDetail.option,
                levelLimit = runeWordsDetail.levelLimit
            )
        }
    }
}
