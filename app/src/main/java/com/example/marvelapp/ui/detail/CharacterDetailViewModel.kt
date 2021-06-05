package com.example.marvelapp.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.marvelapp.api.MarvelRepository

class CharacterDetailViewModel @ViewModelInject constructor(private val repository: MarvelRepository): ViewModel() {

    fun getCharacterComics(characterId: String) = repository.getCharacterComics(characterId).asLiveData().cachedIn(viewModelScope)

    fun getCharacterSeries(characterId: String) = repository.getCharacterSeries(characterId).asLiveData().cachedIn(viewModelScope)

}