package com.example.main.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.PopularMovie
import com.example.main.databinding.ItemMovieCardBinding
import com.example.utility.constant.UrlConfig

class PopularMovieListAdapter(private val onItemClick: (Int) -> Unit) :
    ListAdapter<PopularMovie, PopularMovieListAdapter.MovieListVH>(MovieListDiffUtil) {
    inner class MovieListVH(private val binding: ItemMovieCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PopularMovie) {
            val context = binding.root.context
            binding.tvTitle.text = item.title
            binding.tvDate.text = item.releaseDate
            val imagePath = UrlConfig.IMAGE_URL.value + item.posterPath
            Glide.with(context).load(imagePath).into(binding.ivMovie)
            binding.root.setOnClickListener {
                onItemClick.invoke(item.id ?: 0)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListVH {
        val binding =
            ItemMovieCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListVH(binding)
    }

    override fun onBindViewHolder(holder: MovieListVH, position: Int) {
        holder.bind(getItem(position))
    }
}

object MovieListDiffUtil : DiffUtil.ItemCallback<PopularMovie>() {
    override fun areItemsTheSame(oldItem: PopularMovie, newItem: PopularMovie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PopularMovie, newItem: PopularMovie): Boolean {
        return oldItem.id == newItem.id && oldItem.title == newItem.title
                && oldItem.posterPath == newItem.posterPath && oldItem.releaseDate == newItem.releaseDate
    }

}