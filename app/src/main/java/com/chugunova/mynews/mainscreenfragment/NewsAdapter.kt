package com.chugunova.mynews.mainscreenfragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chugunova.mynews.R
import com.chugunova.mynews.model.Articles
import com.chugunova.mynews.utils.GlideApp
import java.util.*
import kotlinx.android.synthetic.main.news_item.view.newsItem
import kotlinx.android.synthetic.main.news_item.view.newsItemTitle

class NewsAdapter(val adapterOnClick: (Int) -> Unit) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var newsItems = ArrayList<Articles>()
    private lateinit var context: Context

    fun addNewsItems(newsItems: ArrayList<Articles>) {
        this.newsItems.addAll(newsItems)
        this.notifyDataSetChanged()
    }

    fun deleteNewsItems() {
        this.newsItems.clear()
        this.notifyDataSetChanged()
    }

    fun getNewsItems(): ArrayList<Articles> {
        return newsItems
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            val news = newsItems[bindingAdapterPosition]
            GlideApp.with(itemView.context)
                .load(news.urlToImage)
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.spinner_ring)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontTransform()
                .into(itemView.newsItem)

            itemView.apply {
                newsItemTitle.text = news.title
                setOnClickListener {
                    adapterOnClick(bindingAdapterPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return newsItems.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}