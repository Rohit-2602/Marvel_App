package com.example.marvelapp.ui.allcharacters

import android.annotation.SuppressLint
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
import com.example.marvelapp.data.CharacterResult
import com.example.marvelapp.databinding.ItemCharacterBinding
import com.example.marvelapp.util.Comparator.CHARACTER_COMPARATOR

class AllCharacterAdapter(private val listener: OnClickListener) :
    PagingDataAdapter<CharacterResult, AllCharacterAdapter.AllCharacterViewHolder>(CHARACTER_COMPARATOR) {

    inner class AllCharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.characterCardView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val character = getItem(position)
                    if (character != null) {
                        listener.onClick(character)
                    }
                }
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(character: CharacterResult) {
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
                            characterProgressbar.isVisible = false
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            characterProgressbar.isVisible = false
                            return false
                        }
                    })
                    .centerCrop().into(characterImage)
                characterName.text = character.name
                val description = character.description
                if (description == "") {
                    characterDescription.text = "No Description"
                }
                else {
                    characterDescription.text = character.description
                }
                characterComics.text = "Comics: ${character.comics.available}"
                characterSeries.text = "Series: ${character.series.available}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllCharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllCharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllCharacterViewHolder, position: Int) {
        val currentCharacter = getItem(position)
        if (currentCharacter != null) {
            holder.bind(currentCharacter)
        }
    }
}

interface OnClickListener {
    fun onClick(character: CharacterResult)
}