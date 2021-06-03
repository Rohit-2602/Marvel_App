package com.example.marvelapp.ui.allhero

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvelapp.R
import com.example.marvelapp.databinding.FragmentAllCharactersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllCharacterFragment : Fragment(R.layout.fragment_all_characters) {

    private val viewModel by viewModels<AllCharacterViewModel>()
    private var _binding: FragmentAllCharactersBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAllCharactersBinding.bind(view)

        val allCharacterAdapter = AllCharacterAdapter()

        allCharacterAdapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerview.isVisible = loadState.source.refresh is LoadState.NotLoading

                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached && allCharacterAdapter.itemCount < 1) {
                    recyclerview.isVisible = false
                    noResultFoundTextView.isVisible = true
                }
                else {
                    noResultFoundTextView.isVisible = false
                }
            }
        }

        binding.apply {
            recyclerview.apply {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = allCharacterAdapter.withLoadStateHeaderAndFooter(
                    header = MarvelLoadStateAdapter { allCharacterAdapter.retry() },
                    footer = MarvelLoadStateAdapter { allCharacterAdapter.retry() }
                )
                setHasFixedSize(true)
            }
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    viewModel.searchQuery.value = newText
                }
                return true
            }
        })

        viewModel.searchResult.observe(viewLifecycleOwner) {
            allCharacterAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}