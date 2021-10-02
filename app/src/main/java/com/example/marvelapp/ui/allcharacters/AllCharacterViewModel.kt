package com.example.marvelapp.ui.allcharacters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.marvelapp.api.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class AllCharacterViewModel @Inject constructor(private val marvelRepository: MarvelRepository) :
    ViewModel() {

    val searchQuery = MutableStateFlow("")

    val searchResult = searchQuery.flatMapLatest { query ->
        marvelRepository.searchCharacter(query).cachedIn(viewModelScope)
    }.asLiveData()

}