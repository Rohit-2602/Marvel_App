package com.example.marvelapp.ui.charactercomics

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelapp.R
import com.example.marvelapp.databinding.FragmentComicBinding
import com.example.marvelapp.ui.allcharacters.MarvelLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicFragment(private val characterId: String): Fragment(R.layout.fragment_comic) {

    private var _binding: FragmentComicBinding ?= null
    private val binding get() = _binding!!
    private val comicViewModel by viewModels<ComicViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentComicBinding.bind(view)

        val comicAdapter = ComicRecyclerViewAdapter()

        comicAdapter.addLoadStateListener { loadState ->
            binding.apply {
                comicProgressBar.isVisible = loadState.source.refresh is LoadState.Loading
                comicRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached && comicAdapter.itemCount < 1) {
                    comicRecyclerView.isVisible = false
                    noResultFoundTextView.isVisible = true
                }
                else {
                    noResultFoundTextView.isVisible = false
                }
            }
        }

        binding.apply {
            comicRecyclerView.adapter = comicAdapter.withLoadStateHeaderAndFooter(
                header = MarvelLoadStateAdapter { comicAdapter.retry() },
                footer = MarvelLoadStateAdapter { comicAdapter.retry() }
            )
            comicRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            comicRecyclerView.setHasFixedSize(true)
        }

        comicViewModel.getCharacterComics(characterId).observe(viewLifecycleOwner) {
            comicAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}