package com.example.marvelapp.ui.characterdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.marvelapp.R
import com.example.marvelapp.databinding.FragmentCharacterDetailBinding
import com.example.marvelapp.ui.charactercomics.ComicFragment
import com.example.marvelapp.ui.characterseries.SeriesFragment
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

        val character = navArgs.character

        val fragments = arrayListOf(
            ComicFragment(character.id),
            SeriesFragment(character.id)
        )

        val viewPagerAdapter = ViewPagerAdapter(fragments, requireActivity())

        binding.viewPager.adapter = viewPagerAdapter
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Comics"
                1 -> tab.text = "Series"
            }
        }.attach()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}