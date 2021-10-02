package com.example.marvelapp.ui.characterseries

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.marvelapp.data.SeriesResult
import com.example.marvelapp.databinding.ItemSeriesBinding
import com.example.marvelapp.util.Comparator.CHARACTER_SERIES_COMPARATOR

class SeriesRecyclerViewAdapter :
    PagingDataAdapter<SeriesResult, SeriesRecyclerViewAdapter.ComicViewHolder>(CHARACTER_SERIES_COMPARATOR) {

    inner class ComicViewHolder(private val binding: ItemSeriesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(series: SeriesResult) {
            binding.apply {
                Glide.with(itemView)
                    .load(series.thumbnail.path + "." + series.thumbnail.extension)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            seriesProgressbar.isVisible = false
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            seriesProgressbar.isVisible = false
                            return false
                        }
                    })
                    .centerCrop().into(seriesImage)
                seriesName.text = series.title
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