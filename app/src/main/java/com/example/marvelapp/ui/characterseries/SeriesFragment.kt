package com.example.marvelapp.ui.characterseries

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelapp.R
import com.example.marvelapp.databinding.FragmentSeriesBinding
import com.example.marvelapp.ui.allcharacters.MarvelLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeriesFragment(private val characterId: String) : Fragment(R.layout.fragment_series) {

    private var _binding: FragmentSeriesBinding? = null
    private val binding get() = _binding!!
    private val seriesViewModel by viewModels<SeriesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSeriesBinding.bind(view)

        val seriesAdapter = SeriesRecyclerViewAdapter()

        seriesAdapter.addLoadStateListener { loadState ->
            binding.apply {
                seriesProgressBar.isVisible = loadState.source.refresh is LoadState.Loading
                seriesRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached && seriesAdapter.itemCount < 1
                ) {
                    seriesRecyclerView.isVisible = false
                    noResultFoundTextView.isVisible = true
                } else {
                    noResultFoundTextView.isVisible = false
                }
            }
        }

        binding.apply {
            seriesRecyclerView.adapter = seriesAdapter.withLoadStateHeaderAndFooter(
                header = MarvelLoadStateAdapter { seriesAdapter.retry() },
                footer = MarvelLoadStateAdapter { seriesAdapter.retry() }
            )
            seriesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            seriesRecyclerView.setHasFixedSize(true)
        }

        seriesViewModel.getCharacterSeries(characterId).observe(viewLifecycleOwner) {
            seriesAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}