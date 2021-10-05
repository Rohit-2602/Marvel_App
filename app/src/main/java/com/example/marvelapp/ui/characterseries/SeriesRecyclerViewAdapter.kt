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

class SeriesRecyclerViewAdapter(private val listener: SeriesClickListener) :
    PagingDataAdapter<SeriesResult, SeriesRecyclerViewAdapter.SeriesViewHolder>(CHARACTER_SERIES_COMPARATOR) {

    inner class SeriesViewHolder(private val binding: ItemSeriesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(series: SeriesResult, position: Int) {
            binding.apply {
                if (position % 2 == 1) {
                    seriesCardView.rotationY = 180f
                    seriesImage.rotationY = 180f
                    seriesName.rotationY = 180f
                    seriesDescription.rotationY = 180f
                }
                else {
                    seriesCardView.rotationY = 0f
                    seriesImage.rotationY = 0f
                    seriesName.rotationY = 0f
                    seriesDescription.rotationY = 0f
                }
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val binding = ItemSeriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val seriesViewHolder = SeriesViewHolder(binding)
        binding.seriesCardView.setOnClickListener {
            listener.showSeriesDetail(getItem(seriesViewHolder.absoluteAdapterPosition)!!)
        }
        return seriesViewHolder
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, position)
        }
    }
}

interface SeriesClickListener {
    fun showSeriesDetail(series: SeriesResult)
}