package com.example.marvelapp.ui.allhero

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.marvelapp.api.MarvelApi
import com.example.marvelapp.data.CharacterPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AllCharacterRepository @Inject constructor (private val marvelApi: MarvelApi) {

    fun getCharacters() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100, enablePlaceholders = false),
        pagingSourceFactory = { CharacterPagingSource(marvelApi) }
    ).liveData

}