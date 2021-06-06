package com.example.marvelapp.ui.series

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelapp.data.SeriesResult
import com.example.marvelapp.databinding.ItemSeriesBinding

// Used in CharacterDetailFragment
class SeriesRecyclerViewAdapter :
    PagingDataAdapter<SeriesResult, SeriesRecyclerViewAdapter.ComicViewHolder>(COMPARATOR_COMICS) {

    companion object {
        private val COMPARATOR_COMICS = object : DiffUtil.ItemCallback<SeriesResult>() {
            override fun areItemsTheSame(oldItem: SeriesResult, newItem: SeriesResult): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SeriesResult, newItem: SeriesResult): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }

    inner class ComicViewHolder(private val binding: ItemSeriesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(series: SeriesResult) {
            binding.apply {
                Glide.with(itemView).load(series.thumbnail.path + "." + series.thumbnail.extension)
                    .into(seriesThumbnail)
                seriesTitle.text = series.title
                seriesDescription.text = series.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val binding = ItemSeriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }
}