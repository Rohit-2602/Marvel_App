package com.example.marvelapp.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.databinding.ViewpagerItemBinding

class PagerAdapter(val adapter: List<RecyclerviewAdapter>): RecyclerView.Adapter<PagerAdapter.PagerViewHolder>() {

    inner class PagerViewHolder(private val binding: ViewpagerItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(adapter: RecyclerviewAdapter) {
            binding.apply {
                pagerRecyclerview.adapter = adapter
                pagerRecyclerview.layoutManager = LinearLayoutManager(root.context)
                pagerRecyclerview.setHasFixedSize(true)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val binding = ViewpagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val currentAdapter = adapter[position]
        holder.bind(currentAdapter)
    }

    override fun getItemCount(): Int {
        return adapter.size
    }
}