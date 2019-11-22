package com.news.headline.db.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "News")
data class News(
    var source: Source,
    var title: String?,
    var description: String?,
    var urlToImage: String?,
    var publishedAt: String?
):Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Int = 0
}

data class Source(val name: String?): Serializable