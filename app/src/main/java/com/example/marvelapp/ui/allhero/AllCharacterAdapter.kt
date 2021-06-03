package com.example.marvelapp.ui.allhero

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.marvelapp.data.MarvelHero
import com.example.marvelapp.databinding.ItemHeroesBinding

class AllCharacterAdapter :
    PagingDataAdapter<MarvelHero, AllCharacterAdapter.AllCharacterViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<MarvelHero>() {
            override fun areItemsTheSame(oldItem: MarvelHero, newItem: MarvelHero): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MarvelHero, newItem: MarvelHero): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

    class AllCharacterViewHolder(private val binding: ItemHeroesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: MarvelHero) {
            binding.apply {
                Glide.with(itemView)
                    .load(character.thumbnail.path + "." + character.thumbnail.extension)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            heroProgressBar.isVisible = false
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            heroProgressBar.isVisible = false
                            return false
                        }
                    })
                    .centerCrop().into(heroImageView)
                heroNameTV.text = character.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllCharacterViewHolder {
        val binding = ItemHeroesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = AllCharacterViewHolder(binding)
        binding.cardView.setOnClickListener {

        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: AllCharacterViewHolder, position: Int) {
        val currentCharacter = getItem(position)
        if (currentCharacter != null) {
            holder.bind(currentCharacter)
        }
    }
}