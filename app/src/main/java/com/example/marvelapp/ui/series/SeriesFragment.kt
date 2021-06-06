package com.example.marvelapp.ui.series

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelapp.R
import com.example.marvelapp.databinding.FragmentSeriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeriesFragment(private val characterId: String) : Fragment(R.layout.fragment_series) {

    private var _binding: FragmentSeriesBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<SeriesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSeriesBinding.bind(view)

        val seriesAdapter = SeriesRecyclerViewAdapter()

        binding.apply {
            seriesRecyclerView.adapter = seriesAdapter
            seriesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            seriesRecyclerView.setHasFixedSize(true)
        }

        viewModel.getCharacterSeries(characterId).observe(viewLifecycleOwner) {
            seriesAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}