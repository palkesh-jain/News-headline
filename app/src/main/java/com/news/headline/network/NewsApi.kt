package com.news.headline.network

import com.news.headline.db.models.News
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NewsApi {

    @GET("/v2/top-headlines")
    fun getNews(@Header("X-Api-Key") apiKey: String, @Query("country") country: String = "in"): Observable<List<News>>

}