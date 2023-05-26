package com.example.newsapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.data.model.response.Article
import com.example.newsapp.databinding.ArticleListItemBinding


class NewsAdapter : ListAdapter<Article, NewsViewHolder>(ArticlesDiffCallBack()) {
    fun getList(): List<Article> = currentList
    fun setList(list: MutableList<Article>) {
        submitList(null)
        submitList(list)
    }


    private var onItemClickListener: ((Article) -> Unit)? = null
    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ArticleListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemId(position: Int): Long {
        return getList()[position].hashCode().toLong()
    }

    override fun getItemCount(): Int = getList().size


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(currentList[position], onItemClickListener)
    }
}

class NewsViewHolder(private val binding: ArticleListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Article, onItemClickListener: ((Article) -> Unit)?) {
        binding.article = item
        binding.executePendingBindings()

        binding.root.setOnClickListener {
            onItemClickListener?.invoke(item)
        }
    }
}


private class ArticlesDiffCallBack : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
        oldItem.hashCode() == newItem.hashCode()

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
        oldItem == newItem
}