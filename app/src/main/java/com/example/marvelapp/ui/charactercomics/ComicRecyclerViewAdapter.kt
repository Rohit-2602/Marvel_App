package com.example.marvelapp.ui.charactercomics

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
import com.example.marvelapp.data.ComicResult
import com.example.marvelapp.databinding.ItemComicBinding
import com.example.marvelapp.util.Comparator.CHARACTER_COMICS_COMPARATOR

class ComicRecyclerViewAdapter(private val listener: ComicClickListener) :
    PagingDataAdapter<ComicResult, ComicRecyclerViewAdapter.ComicViewHolder>(CHARACTER_COMICS_COMPARATOR) {

    inner class ComicViewHolder(private val binding: ItemComicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(comic: ComicResult, position: Int) {
            binding.apply {
                if (position % 2 == 1) {
                    comicCardView.rotationY = 180f
                    comicImage.rotationY = 180f
                    comicName.rotationY = 180f
                    comicDescription.rotationY = 180f
                }
                else {
                    comicCardView.rotationY = 0f
                    comicImage.rotationY = 0f
                    comicName.rotationY = 0f
                    comicDescription.rotationY = 0f
                }
                Glide.with(itemView)
                    .load(comic.thumbnail.path + "." + comic.thumbnail.extension)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            comicProgressbar.isVisible = false
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            comicProgressbar.isVisible = false
                            return false
                        }
                    })
                    .centerCrop().into(comicImage)
                comicName.text = comic.title
                comicDescription.text = comic.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val binding = ItemComicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val comicViewHolder = ComicViewHolder(binding)
        binding.comicCardView.setOnClickListener {
            listener.showComicDetail(getItem(comicViewHolder.absoluteAdapterPosition)!!)
        }
        return comicViewHolder
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, position)
        }
    }
}

interface ComicClickListener {
    fun showComicDetail(comic: ComicResult)
}