package com.example.marvelapp.ui.favouritecharacters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.api.MarvelRepository
import com.example.marvelapp.data.CharacterResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteCharacterViewModel @Inject constructor(private val marvelRepository: MarvelRepository) :
    ViewModel() {

    fun addToFavourite(characterResult: CharacterResult) =
        viewModelScope.launch {
            marvelRepository.addCharacterToFavourite(characterResult)
        }

    fun removeFromFavourite(characterResult: CharacterResult) =
        viewModelScope.launch {
            marvelRepository.removeCharacterFromFavourite(characterResult)
        }

    fun getFavouriteCharacters() =
        marvelRepository.getFavouriteCharacters().asLiveData()

}