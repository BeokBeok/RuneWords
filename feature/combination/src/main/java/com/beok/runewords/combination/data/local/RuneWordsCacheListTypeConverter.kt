package com.beok.runewords.combination.data.local

import androidx.room.TypeConverter
import com.beok.runewords.combination.data.model.RuneWordsCache
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal class RuneWordsCacheListTypeConverter {
    @TypeConverter
    fun toJson(value: List<RuneWordsCache>): String = Json.encodeToString(value)

    @TypeConverter
    fun fromJson(value: String): List<RuneWordsCache> = Json.decodeFromString(value)
}
