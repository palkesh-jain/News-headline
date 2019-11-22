package com.news.headline

import com.news.headline.db.NewsDao
import com.news.headline.db.models.News
import com.news.headline.network.NewsApi
import com.news.headline.utils.Constants
import com.news.headline.utils.NetworkUtils
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class NewsRepository @Inject constructor(
    val newsApi: NewsApi,
    val newsDao: NewsDao,
    val netUtils: NetworkUtils
) {

    fun getNews(): Observable<List<News>> {
        val observableFromApi = getNewsFromApi()
        val observableFromDb = getNewsFromDb()
        return if (netUtils.isConnectedToInternet()) {
            observableFromApi
        } else {
            observableFromDb
        }
    }

    private fun getNewsFromApi(): Observable<List<News>> {
        return newsApi.getNews(Constants.API_KEY)
            .doOnNext {
                for (item in it) {
                    newsDao.insert(item)
                }
            }
    }

    private fun getNewsFromDb(): Observable<List<News>> {
        return newsDao.getAllNews()
            .toObservable()
    }

}