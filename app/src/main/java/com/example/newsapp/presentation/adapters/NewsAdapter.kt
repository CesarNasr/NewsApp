package com.example.newsapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.data.model.response.Article
import com.example.newsapp.databinding.ArticleListItemBinding


class NewsAdapter : ListAdapter<Article, TripViewHolder>(TripsDiffCallBack()) {
    fun getList(): List<Article> = currentList
    fun setList(list: MutableList<Article>) = submitList(list)


    private var onItemClickListener: ((Article) -> Unit)? = null
    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        return TripViewHolder(
            ArticleListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemId(position: Int): Long {
        return getList()[position].hashCode().toLong()
    }

    override fun getItemCount(): Int = getList().size


    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}

class TripViewHolder(private val binding: ArticleListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Article) {
        binding.article = item
        binding.executePendingBindings()
    }
}


private class TripsDiffCallBack : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
        oldItem.hashCode() == newItem.hashCode()

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
        oldItem == newItem
}