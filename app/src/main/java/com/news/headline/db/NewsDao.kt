package com.news.headline.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.news.headline.db.models.News
import io.reactivex.Single

@Dao
interface NewsDao {

    @Insert
    fun insert(news: News)

    @Query("DELETE from News")
    fun deleteAllNews()

    @Query("SELECT * FROM News")
    fun getAllNews(): Single<List<News>>
}