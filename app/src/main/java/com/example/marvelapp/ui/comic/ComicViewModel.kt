package com.example.marvelapp.ui.comic

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.marvelapp.api.MarvelRepository

class ComicViewModel @ViewModelInject constructor(private val repository: MarvelRepository): ViewModel() {

    fun getCharacterComics(characterId: String) = repository.getCharacterComics(characterId).asLiveData().cachedIn(viewModelScope)

}