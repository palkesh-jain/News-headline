package com.news.headline

import android.app.Activity
import android.app.Application
import com.news.headline.di.AppModule
import com.news.headline.di.DaggerAppComponent
import com.news.headline.di.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class NewsApplication: Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().appModule(AppModule(this)).networkModule(NetworkModule).build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

}