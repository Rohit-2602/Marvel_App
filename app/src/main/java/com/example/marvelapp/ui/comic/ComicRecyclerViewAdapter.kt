package com.example.marvelapp.ui.comic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelapp.data.ComicResult
import com.example.marvelapp.databinding.ItemComicBinding

// Used in CharacterDetailFragment
class ComicRecyclerViewAdapter :
    PagingDataAdapter<ComicResult, ComicRecyclerViewAdapter.ComicViewHolder>(COMPARATOR_COMICS) {

    companion object {
        private val COMPARATOR_COMICS = object : DiffUtil.ItemCallback<ComicResult>() {
            override fun areItemsTheSame(oldItem: ComicResult, newItem: ComicResult): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ComicResult, newItem: ComicResult): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }

    inner class ComicViewHolder(private val binding: ItemComicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(comic: ComicResult) {
            binding.apply {
                Glide.with(itemView).load(comic.thumbnail.path + "." + comic.thumbnail.extension)
                    .into(comicThumbnail)
                comicTitle.text = comic.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val binding = ItemComicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }
}