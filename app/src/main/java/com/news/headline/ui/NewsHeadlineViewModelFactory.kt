package com.news.headline.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.news.headline.ui.newsheadline.NewsHeadlineViewModel
import javax.inject.Inject

class NewsHeadlineViewModelFactory @Inject constructor(
    private val newsHeadlineViewModel: NewsHeadlineViewModel
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsHeadlineViewModel::class.java)) {
            return newsHeadlineViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}