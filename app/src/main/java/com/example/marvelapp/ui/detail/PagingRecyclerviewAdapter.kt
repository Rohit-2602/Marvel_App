package com.example.marvelapp.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.databinding.ViewpagerRecyclerviewBinding
import com.example.marvelapp.ui.comic.ComicRecyclerViewAdapter
import com.example.marvelapp.ui.series.SeriesRecyclerViewAdapter

class PagingRecyclerviewAdapter(private val comicAdapter: ComicRecyclerViewAdapter, private val seriesAdapter: SeriesRecyclerViewAdapter) :
    RecyclerView.Adapter<PagingRecyclerviewAdapter.PagingRecyclerViewHolder>() {

    class PagingRecyclerViewHolder(val binding: ViewpagerRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingRecyclerViewHolder {
        val binding = ViewpagerRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagingRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PagingRecyclerViewHolder, position: Int) {
        when (position) {
            0 -> {
                holder.binding.apply {
                    pagingRecyclerview.adapter = comicAdapter
                    pagingRecyclerview.layoutManager = LinearLayoutManager(root.context)
                    pagingRecyclerview.setHasFixedSize(true)
                }
            }
            1 -> {
                holder.binding.apply {
                    pagingRecyclerview.adapter = seriesAdapter
                    pagingRecyclerview.layoutManager = LinearLayoutManager(root.context)
                    pagingRecyclerview.setHasFixedSize(true)
                }
            }
            2 -> holder.binding.apply {
                pagingRecyclerview.adapter = comicAdapter
                pagingRecyclerview.layoutManager = LinearLayoutManager(root.context)
                pagingRecyclerview.setHasFixedSize(true)
            }
        }
        comicAdapter.notifyDataSetChanged()
        seriesAdapter.notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return 3
    }
}