package com.news.headline.ui.newsheadline

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.news.headline.R
import com.news.headline.db.models.News
import kotlinx.android.synthetic.main.item_news_headline.view.*

class NewsHeadlineAdapter(
    private val newsList: List<News>,
    private val listener: (Int) -> Unit
) : RecyclerView.Adapter<NewsHeadlineAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news_headline, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        holder.ivBg.setImageDrawable(null)

        holder.tvHeadline.text = news.title
        holder.tvDate.text = news.publishedAt?.substring(0,10)
        holder.tvSource.text = news.source?.name
        holder.tvHeadline.text = news.title
        Glide.with(holder.itemView).load(news.urlToImage).into(holder.ivBg)

        holder.bind(holder.container, position)
    }

    inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val container = view.container
        val ivBg = view.iv_news_headline_bg
        val tvHeadline = view.tv_news_headline
        val tvDate = view.tv_news_date
        val tvSource = view.tv_news_source

        fun bind(view: View, pos: Int) {
            view.setOnClickListener {
                listener(pos)
            }

        }
    }

}
