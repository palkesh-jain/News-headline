package com.news.headline.di

import android.app.Application
import android.content.Context
import com.news.headline.NewsApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        BuildersModule::class,
        AppModule::class,
        NetworkModule::class
    ]
)
interface AppComponent {
    fun inject(app: NewsApplication)
}