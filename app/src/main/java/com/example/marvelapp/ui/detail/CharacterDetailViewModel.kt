package com.example.marvelapp.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.marvelapp.api.MarvelRepository

class CharacterDetailViewModel @ViewModelInject constructor(private val repository: MarvelRepository): ViewModel() {

    val comics = repository

}