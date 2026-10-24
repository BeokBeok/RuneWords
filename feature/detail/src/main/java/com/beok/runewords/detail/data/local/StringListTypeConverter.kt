package com.beok.runewords.detail.data.local

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal class StringListTypeConverter {
    @TypeConverter
    fun toJson(value: List<String>): String = Json.encodeToString(value)

    @TypeConverter
    fun fromJson(value: String): List<String> = Json.decodeFromString(value)
}
