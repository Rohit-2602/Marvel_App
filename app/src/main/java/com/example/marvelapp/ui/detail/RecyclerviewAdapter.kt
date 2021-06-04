package com.example.marvelapp.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelapp.data.Comics.ComicListItem
import com.example.marvelapp.databinding.ItemDetailBinding

class RecyclerviewAdapter: ListAdapter<ComicListItem, RecyclerviewAdapter.ComicViewHolder>(COMPARATOR_COMICS) {
    companion object {
        private val COMPARATOR_COMICS = object : DiffUtil.ItemCallback<ComicListItem>() {
            override fun areItemsTheSame(oldItem: ComicListItem, newItem: ComicListItem): Boolean {
                return oldItem.resourceURI == newItem.resourceURI
            }

            override fun areContentsTheSame(oldItem: ComicListItem, newItem: ComicListItem): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

    inner class ComicViewHolder(private val binding: ItemDetailBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(comic: ComicListItem) {
            binding.apply {
                Glide.with(itemView).load(comic.resourceURI).into(imageViewPager)
                textViewPager.text = comic.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val binding = ItemDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }
}