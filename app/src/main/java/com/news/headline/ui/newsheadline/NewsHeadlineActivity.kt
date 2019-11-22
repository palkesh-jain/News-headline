package com.news.headline.ui.newsheadline

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.news.headline.R
import com.news.headline.db.models.News
import com.news.headline.ui.NewsHeadlineViewModelFactory
import com.news.headline.ui.newsdetail.NewsDetailActivity
import com.news.headline.utils.VerticalSpaceItemDecoration
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_news_headline.*
import javax.inject.Inject

class NewsHeadlineActivity : AppCompatActivity() {

    @Inject
    lateinit var newsHeadlineViewModelFactory: NewsHeadlineViewModelFactory
    lateinit var newsHeadlineViewModel: NewsHeadlineViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_headline)

        AndroidInjection.inject(this)

        // Setting TypeFace
        tv_headline.typeface = Typeface.createFromAsset(assets, "roboto_slab_bold.ttf")

        newsHeadlineViewModel = ViewModelProviders.of(this, newsHeadlineViewModelFactory)
            .get(NewsHeadlineViewModel::class.java)

        rv_news_headlines.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        showShimmer()
        newsHeadlineViewModel.loadNews()

        newsHeadlineViewModel.newsList().observe(this,
            Observer<List<News>> {
                if (it.isEmpty()) {
                    btn_try_again.visibility = View.VISIBLE
                    tv_no_internet.visibility = View.VISIBLE
                } else {
                    btn_try_again.visibility = View.GONE
                    tv_no_internet.visibility = View.GONE
                    prepareRecyclerView(it)
                }
                hideShimmer()
            })

        newsHeadlineViewModel.newsListError().observe(this, Observer<String> {
            btn_try_again.visibility = View.VISIBLE
            tv_no_internet.visibility = View.VISIBLE
            hideShimmer()

            btn_try_again.setOnClickListener {
                newsHeadlineViewModel.loadNews()
            }
        })
    }

    private fun prepareRecyclerView(newsList: List<News>) {
        rv_news_headlines.adapter =
            NewsHeadlineAdapter(newsList) {
                val intent = Intent(this@NewsHeadlineActivity, NewsDetailActivity::class.java)
                intent.putExtra("data", newsList[it])
                startActivity(intent)
            }
        rv_news_headlines.layoutManager = LinearLayoutManager(this)
        rv_news_headlines.addItemDecoration(
            VerticalSpaceItemDecoration(
                resources.getDimension(R.dimen.news_headline_recycler_view_item_spacing).toInt(),
                newsList.size - 1
            )
        )
    }

    private fun showShimmer() {
        shimmer.startShimmer()
        shimmer.visibility = View.VISIBLE
    }

    private fun hideShimmer() {
        shimmer.stopShimmer()
        shimmer.visibility = View.GONE
    }

    override fun onDestroy() {
        newsHeadlineViewModel.disposeElements()
        super.onDestroy()

    }
}
