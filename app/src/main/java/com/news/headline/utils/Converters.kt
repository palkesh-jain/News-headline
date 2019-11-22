package com.news.headline.utils

import androidx.room.TypeConverter
import com.news.headline.db.models.Source

class Converters{

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name ?: "Unknown"
    }

    @TypeConverter
    fun toSource(sourceName: String): Source {
        return Source(sourceName)
    }

}