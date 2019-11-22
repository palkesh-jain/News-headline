package com.news.headline.ui.newsheadline

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.news.headline.NewsRepository
import com.news.headline.db.models.News
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NewsHeadlineViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    val TAG = NewsHeadlineViewModel::class.java.simpleName

    var newsList: MutableLiveData<List<News>> = MutableLiveData()
    var newsListError: MutableLiveData<String> = MutableLiveData()
    lateinit var disposableObserver: DisposableObserver<List<News>>

    fun newsList(): LiveData<List<News>> {
        return newsList
    }

    fun newsListError(): LiveData<String> {
        return newsListError
    }

    fun loadNews() {
        disposableObserver = object : DisposableObserver<List<News>>() {
            override fun onComplete() {

            }

            override fun onNext(news: List<News>) {
                newsList.postValue(news)
            }

            override fun onError(e: Throwable) {
                newsListError.postValue(e.message)
            }
        }

        newsRepository.getNews()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(400, TimeUnit.MILLISECONDS)
            .subscribe(disposableObserver)
    }

    fun disposeElements() {
        if (null != disposableObserver && !disposableObserver.isDisposed) disposableObserver.dispose()
    }

}