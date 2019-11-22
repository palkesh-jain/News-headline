package com.news.headline.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.news.headline.db.models.News
import com.news.headline.utils.Converters

@Database(entities = [News::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        private var instance: NewsDatabase? = null

        fun getInstance(context: Context): NewsDatabase? {
            if (instance == null) {
                synchronized(NewsDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NewsDatabase::class.java,
                        "NewsDatabase"
                    ).fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance
        }
    }

}