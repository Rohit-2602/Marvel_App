package com.example.marvelapp.ui.allcharacters

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelapp.R
import com.example.marvelapp.data.CharacterResult
import com.example.marvelapp.databinding.FragmentAllCharactersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllCharacterFragment : Fragment(R.layout.fragment_all_characters), CharacterClickListener {

    private val allCharacterViewModel by viewModels<AllCharacterViewModel>()
    private var _binding: FragmentAllCharactersBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAllCharactersBinding.bind(view)

        val mainActivity = activity as AppCompatActivity
        mainActivity.setSupportActionBar(binding.toolbar)

        setHasOptionsMenu(true)

        val allCharacterAdapter = AllCharacterAdapter(this)

        allCharacterAdapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerview.isVisible = loadState.source.refresh is LoadState.NotLoading
                allCharacterRetryButton.isVisible = loadState.source.refresh is LoadState.Error
                allCharacterNoConnection.isVisible = loadState.source.refresh is LoadState.Error

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
                layoutManager = LinearLayoutManager(requireContext())
                adapter = allCharacterAdapter.withLoadStateHeaderAndFooter(
                    header = MarvelLoadStateAdapter { allCharacterAdapter.retry() },
                    footer = MarvelLoadStateAdapter { allCharacterAdapter.retry() }
                )
                setHasFixedSize(true)
            }
            allCharacterRetryButton.setOnClickListener {
                allCharacterAdapter.retry()
            }
        }

        allCharacterViewModel.getFavouriteCharacters().observe(viewLifecycleOwner) {
            allCharacterAdapter.updateFavourites(it)
        }

        allCharacterViewModel.searchResult.observe(viewLifecycleOwner) {
            allCharacterAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

    }

    override fun onClick(character: CharacterResult) {
        val action = AllCharacterFragmentDirections.actionAllHeroFragmentToCharacterDetailFragment(character)
        findNavController().navigate(action)
    }

    override fun addToFavourite(character: CharacterResult) {
        allCharacterViewModel.addToFavourite(character)
    }

    override fun removeFromFavourite(character: CharacterResult) {
        allCharacterViewModel.removeFromFavourite(character)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.all_character_menu, menu)

        val searchItem = menu.findItem(R.id.all_character_search)
        val searchView = searchItem.actionView as SearchView

        searchView.queryHint = "Search Character"
        searchView.setIconifiedByDefault(false)
        searchView.onActionViewExpanded()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    allCharacterViewModel.searchQuery.value = newText
                }
                return true
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}