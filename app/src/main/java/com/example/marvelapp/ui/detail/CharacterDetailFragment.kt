package com.example.marvelapp.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.marvelapp.R
import com.example.marvelapp.databinding.FragmentCharacterDetailBinding
import com.google.android.material.tabs.TabLayoutMediator

class CharacterDetailFragment: Fragment(R.layout.fragment_character_detail) {

    private var _binding: FragmentCharacterDetailBinding ?= null
    private val binding get() = _binding!!
    private val navArgs by navArgs<CharacterDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCharacterDetailBinding.bind(view)

        val character = navArgs.Character
        val comics = character.comics

        binding.apply {
            Glide.with(requireContext()).load(character.thumbnail.path + "." + character.thumbnail.extension).into(characterImageView)
        }

        val adapter1 = RecyclerviewAdapter()
        val adapter2 = RecyclerviewAdapter()
        val adapter3 = RecyclerviewAdapter()

        adapter1.submitList(comics.items)

        val listAdapter = ArrayList<RecyclerviewAdapter>()
        listAdapter.add(adapter1)
        listAdapter.add(adapter2)
        listAdapter.add(adapter3)

        val pagerAdapter = PagerAdapter(listAdapter)
        binding.viewPager.adapter = pagerAdapter
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Comics"
                1 -> tab.text = "Series"
                2 -> tab.text = "Stories"
            }
        }.attach()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}