package com.news.headline.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.news.headline.db.NewsDao
import com.news.headline.db.NewsDatabase
import com.news.headline.ui.NewsHeadlineViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: Application) {

    @Singleton
    @Provides
    fun provideApplication(): Application = app

    @Singleton
    @Provides
    fun provideContext(): Context = app.applicationContext

    @Singleton
    @Provides
    internal fun provideNewsDatabase(application: Application): NewsDatabase {
        return NewsDatabase.getInstance(application.applicationContext)!!
    }

    @Singleton
    @Provides
    internal fun provideNewsDao(database: NewsDatabase): NewsDao {
        return database.newsDao()
    }

    @Provides
    fun providesNewsViewModelFactory(
        factory: NewsHeadlineViewModelFactory
    ): ViewModelProvider.Factory = factory

}
