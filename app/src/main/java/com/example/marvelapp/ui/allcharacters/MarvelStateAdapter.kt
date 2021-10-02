package com.example.marvelapp.ui.allcharacters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.databinding.LoadStateFooterBinding

class MarvelLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<MarvelLoadStateAdapter.LoadStateViewHolder>() {

    class LoadStateViewHolder(private val binding: LoadStateFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(loadState: LoadState) {
                binding.apply {
                    footerProgressBar.isVisible = loadState is LoadState.Loading
                    retryButton.isVisible = loadState !is LoadState.Loading
                    errorTextView.isVisible = loadState !is LoadState.Loading
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = LoadStateFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.retryButton.setOnClickListener {
            retry.invoke()
        }
        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

}