package com.example.marvelapp.api

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.marvelapp.ui.allhero.CharacterPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelRepository @Inject constructor (private val marvelApi: MarvelApi) {

//    fun getCharacters() = Pager(
//        config = PagingConfig(pageSize = 20, maxSize = 100, enablePlaceholders = false),
//        pagingSourceFactory = { CharacterPagingSource(marvelApi) }
//    ).liveData

    fun searchCharacter(query: String) = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100, enablePlaceholders = false),
        pagingSourceFactory = { CharacterPagingSource(marvelApi, query) }
    ).flow

}