package com.example.marvelapp.ui.allhero

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.marvelapp.api.MarvelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

class AllCharacterViewModel @ViewModelInject constructor(private val repository: MarvelRepository) :
    ViewModel() {

    val searchQuery = MutableStateFlow("")

//    val characters = repository.getCharacters().cachedIn(viewModelScope)

    val searchResult = searchQuery.flatMapLatest { query ->
        repository.searchCharacter(query).cachedIn(viewModelScope)
    }.asLiveData()

}