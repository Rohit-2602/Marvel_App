package com.example.marvelapp.ui.series

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.marvelapp.api.MarvelRepository

class SeriesViewModel @ViewModelInject constructor(private val repository: MarvelRepository): ViewModel() {

    fun getCharacterSeries(characterId: String) = repository.getCharacterSeries(characterId).asLiveData().cachedIn(viewModelScope)

}