package com.news.headline.ui.newsheadline

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.news.headline.R
import com.news.headline.db.models.News
import kotlinx.android.synthetic.main.item_news_headline.view.*

class NewsHeadlineAdapter(
    private val newsList: List<News>,
    private val onNewsClicked: (id: Int) -> Unit
) : ListAdapter<News, NewsViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val holder = NewsViewHolder(parent)
        holder.itemView.setOnClickListener {
            onNewsClicked(holder.adapterPosition)
        }
        return holder
    }

    override fun getItem(position: Int): News {
        return newsList[position]
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = getItem(position)
        holder.ivBg.setImageDrawable(null)

        holder.tvHeadline.text = news.title
        holder.tvDate.text = news.publishedAt?.substring(0, 10)
        holder.tvSource.text = news.source.name
        holder.tvHeadline.text = news.title
        Glide.with(holder.itemView).load(news.urlToImage).into(holder.ivBg)
    }

}

val DIFF_CALLBACK = object : DiffUtil.ItemCallback<News>() {
    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem == newItem
    }
}

class NewsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_news_headline, parent, false)
) {
    val ivBg = itemView.iv_news_headline_bg
    val tvHeadline = itemView.tv_news_headline
    val tvDate = itemView.tv_news_date
    val tvSource = itemView.tv_news_source
}
