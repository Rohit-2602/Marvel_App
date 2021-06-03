package com.example.marvelapp.ui.allhero

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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

        val adapter = AllCharacterAdapter()

        binding.apply {
            recyclerview.adapter = adapter
            recyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
            recyclerview.setHasFixedSize(true)
        }

        viewModel.characters.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}