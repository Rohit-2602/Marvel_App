package com.example.marvelapp.ui.favouritecharacters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelapp.R
import com.example.marvelapp.data.CharacterResult
import com.example.marvelapp.databinding.FragmentFavouriteCharactersBinding
import com.example.marvelapp.ui.allcharacters.CharacterClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteCharacterFragment: Fragment(R.layout.fragment_favourite_characters), CharacterClickListener {

    private var _binding: FragmentFavouriteCharactersBinding? = null
    private val binding get() = _binding!!
    private val favouriteCharacterViewModel by viewModels<FavouriteCharacterViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavouriteCharactersBinding.bind(view)

        val favouriteCharacterAdapter = FavouriteCharacterAdapter(this)

        binding.recyclerview.apply {
            adapter = favouriteCharacterAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        favouriteCharacterViewModel.getFavouriteCharacters().observe(viewLifecycleOwner) {
            favouriteCharacterAdapter.submitList(it)
        }

    }

    override fun onClick(character: CharacterResult) {
        val action = FavouriteCharacterFragmentDirections.actionFavouriteCharacterFragmentToCharacterDetailFragment(character)
        findNavController().navigate(action)
    }

    override fun addToFavourite(character: CharacterResult) {
        favouriteCharacterViewModel.addToFavourite(character)
    }

    override fun removeFromFavourite(character: CharacterResult) {
        favouriteCharacterViewModel.removeFromFavourite(character)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}