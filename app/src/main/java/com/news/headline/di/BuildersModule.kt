package com.news.headline.di

import com.news.headline.ui.newsheadline.NewsHeadlineActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun newsHeadlineActivity(): NewsHeadlineActivity

}