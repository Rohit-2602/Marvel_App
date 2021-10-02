package com.example.marvelapp.ui.characterdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.marvelapp.api.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val repository: MarvelRepository): ViewModel() {

    fun getCharacterComics(characterId: String) = repository.getCharacterComics(characterId).asLiveData().cachedIn(viewModelScope)

    fun getCharacterSeries(characterId: String) = repository.getCharacterSeries(characterId).asLiveData().cachedIn(viewModelScope)

}