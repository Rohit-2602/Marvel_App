package com.example.marvelapp.api

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.marvelapp.data.CharacterResult
import com.example.marvelapp.db.CharacterDao
import com.example.marvelapp.ui.allcharacters.CharacterPagingSource
import com.example.marvelapp.ui.charactercomics.ComicPagingSource
import com.example.marvelapp.ui.characterseries.SeriesPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelRepository @Inject constructor (private val marvelApi: MarvelApi, private val characterDao: CharacterDao) {

    fun searchCharacter(query: String) = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100, enablePlaceholders = false),
        pagingSourceFactory = { CharacterPagingSource(marvelApi, query) }
    ).flow

    fun getCharacterComics(characterId: String) = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 50, enablePlaceholders = false),
        pagingSourceFactory = { ComicPagingSource(marvelApi, characterId) }
    ).flow

    fun getCharacterSeries(characterId: String) = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 50, enablePlaceholders = false),
        pagingSourceFactory = { SeriesPagingSource(marvelApi, characterId) }
    ).flow
    suspend fun addCharacterToFavourite(characterResult: CharacterResult) =
        characterDao.insert(characterResult)

    suspend fun removeCharacterFromFavourite(characterResult: CharacterResult) =
        characterDao.delete(characterResult)

    fun getFavouriteCharacters() =
        characterDao.getFavouriteCharacters()

}