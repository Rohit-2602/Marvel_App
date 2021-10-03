package com.example.marvelapp.ui.characterseries

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.marvelapp.R
import com.example.marvelapp.data.SeriesResult
import com.example.marvelapp.databinding.FragmentSeriesBinding
import com.example.marvelapp.ui.allcharacters.MarvelLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeriesFragment(private val characterId: String) :
    Fragment(R.layout.fragment_series), SeriesClickListener {

    private var _binding: FragmentSeriesBinding? = null
    private val binding get() = _binding!!
    private val seriesViewModel by viewModels<SeriesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSeriesBinding.bind(view)

        val seriesAdapter = SeriesRecyclerViewAdapter(this)

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

        binding.seriesRecyclerView.apply {
            adapter = seriesAdapter.withLoadStateHeaderAndFooter(
                header = MarvelLoadStateAdapter { seriesAdapter.retry() },
                footer = MarvelLoadStateAdapter { seriesAdapter.retry() }
            )
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        binding.apply {
            blackScreen.setOnClickListener {
                blackScreen.visibility = View.GONE
                seriesDetailConstraint.visibility = View.GONE
            }
        }

        seriesViewModel.getCharacterSeries(characterId).observe(viewLifecycleOwner) {
            seriesAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

    }

    override fun showSeriesDetail(series: SeriesResult) {
        binding.apply {
            blackScreen.visibility = View.VISIBLE
            seriesDetailConstraint.visibility = View.VISIBLE
            Glide.with(requireContext()).load(series.thumbnail.path + "." + series.thumbnail.extension).into(seriesImage)
            seriesDescription.text = series.description
            seriesTitle.text = series.title
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}