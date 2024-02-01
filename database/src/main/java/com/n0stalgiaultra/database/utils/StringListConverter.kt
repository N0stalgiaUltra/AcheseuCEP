package com.n0stalgiaultra.database.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class StringListConverter {
    @TypeConverter
    fun fromList(list: List<String>?): String?{
        return list?.let { Json.encodeToString(list) }
    }

    @TypeConverter
    fun toList(jsonString: String?): List<String>?{
        return jsonString?.let { Json.decodeFromString<List<String>>(it)}
    }
}