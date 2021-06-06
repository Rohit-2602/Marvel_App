package com.example.marvelapp.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.marvelapp.R
import com.example.marvelapp.databinding.FragmentCharacterDetailBinding
import com.example.marvelapp.ui.comic.ComicFragment
import com.example.marvelapp.ui.description.CharacterDescription
import com.example.marvelapp.ui.series.SeriesFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment: Fragment(R.layout.fragment_character_detail) {

    private var _binding: FragmentCharacterDetailBinding ?= null
    private val binding get() = _binding!!
    private val navArgs by navArgs<CharacterDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCharacterDetailBinding.bind(view)

        val character = navArgs.Character

        binding.apply {
            Glide.with(requireContext()).load(character.thumbnail.path + "." + character.thumbnail.extension).into(characterImageView)
            characterNameTextView.text = character.name
        }

        val fragments = arrayListOf(
            CharacterDescription(character.description),
            ComicFragment(character.id),
            SeriesFragment(character.id)
        )

        val viewPagerAdapter = ViewPagerAdapter(fragments, requireActivity())

        binding.viewPager.adapter = viewPagerAdapter
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Description"
                1 -> tab.text = "Comics"
                2 -> tab.text = "Series"
            }
        }.attach()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}