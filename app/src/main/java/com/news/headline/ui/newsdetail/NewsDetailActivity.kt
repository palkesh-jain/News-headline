package com.news.headline.ui.newsdetail

import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.news.headline.R
import com.news.headline.db.models.News
import kotlinx.android.synthetic.main.activity_news_detail.*

class NewsDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        val news = intent.getSerializableExtra("data") as News

        // Setting TypeFace
        tv_news_headline_title.typeface = Typeface.createFromAsset(assets, "roboto_slab_bold.ttf")
        tv_news_source.typeface = Typeface.createFromAsset(assets, "roboto_slab_regular.ttf")
        tv_news_date.typeface = Typeface.createFromAsset(assets, "roboto_slab_regular.ttf")
        tv_news_headline_detail.typeface =
            Typeface.createFromAsset(assets, "roboto_slab_regular.ttf")

        tv_news_headline_title.text = news.title
        tv_news_source.text = news.source?.name
        tv_news_date.text = news.publishedAt?.substring(0,10)
        tv_news_headline_detail.text = news.description
        Glide.with(this).load(news.urlToImage).into(iv_bg)

        ib_back.setOnClickListener { finish() }
    }
}
