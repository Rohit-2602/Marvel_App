package com.example.marvelapp.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.marvelapp.R
import com.example.marvelapp.databinding.FragmentCharacterDetailBinding
import com.example.marvelapp.ui.comic.ComicRecyclerViewAdapter
import com.example.marvelapp.ui.series.SeriesRecyclerViewAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment: Fragment(R.layout.fragment_character_detail) {

    private var _binding: FragmentCharacterDetailBinding ?= null
    private val binding get() = _binding!!
    private val navArgs by navArgs<CharacterDetailFragmentArgs>()
    private val viewModel by viewModels<CharacterDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCharacterDetailBinding.bind(view)

        val character = navArgs.Character

        binding.apply {
            Glide.with(requireContext()).load(character.thumbnail.path + "." + character.thumbnail.extension).into(characterImageView)
        }

        val comicAdapter = ComicRecyclerViewAdapter()
        val seriesAdapter = SeriesRecyclerViewAdapter()
        val adapter3 = ComicRecyclerViewAdapter()

        val viewPagerAdapter = PagingRecyclerviewAdapter(comicAdapter, seriesAdapter)

        binding.viewPager.adapter = viewPagerAdapter
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Comics"
                1 -> tab.text = "Series"
                2 -> tab.text = "Stories"
            }
        }.attach()

        viewModel.getCharacterComics(characterId = character.id).observe(viewLifecycleOwner) {
            comicAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            comicAdapter.notifyDataSetChanged()
        }

        viewModel.getCharacterSeries(characterId = character.id).observe(viewLifecycleOwner) {
            seriesAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            seriesAdapter.notifyDataSetChanged()
        }

        viewModel.getCharacterComics(characterId = character.id).observe(viewLifecycleOwner) {
            adapter3.submitData(viewLifecycleOwner.lifecycle, it)
            adapter3.notifyDataSetChanged()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}