package com.beok.runewords.combination.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rune")
internal data class RuneTable constructor(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "runewords")
    val runewords: List<String>
)
