package com.example.marvelapp.ui.description

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.marvelapp.R
import com.example.marvelapp.databinding.FragmentDescriptionBinding

class CharacterDescription(private val characterDescription: String) : Fragment(R.layout.fragment_description) {

    private var _binding: FragmentDescriptionBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDescriptionBinding.bind(view)

        binding.apply {
            characterDescriptionTextView.text = characterDescription
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}