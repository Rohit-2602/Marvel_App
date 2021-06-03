package com.example.marvelapp.ui.allhero

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn

class AllCharacterViewModel @ViewModelInject constructor(private val repository: AllCharacterRepository) :
    ViewModel() {

    val characters = repository.getCharacters().cachedIn(viewModelScope)

}