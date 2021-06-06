package com.example.marvelapp.ui.comic

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelapp.R
import com.example.marvelapp.databinding.FragmentComicBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicFragment(private val characterId: String): Fragment(R.layout.fragment_comic) {

    private var _binding: FragmentComicBinding ?= null
    private val binding get() = _binding!!
    private val viewModel by viewModels<ComicViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentComicBinding.bind(view)

        val comicAdapter = ComicRecyclerViewAdapter()

        binding.apply {
            comicRecyclerView.adapter = comicAdapter
            comicRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            comicRecyclerView.setHasFixedSize(true)
        }

        viewModel.getCharacterComics(characterId).observe(viewLifecycleOwner) {
            comicAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}