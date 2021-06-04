package com.example.marvelapp.ui.allhero

import android.util.Log
import androidx.paging.PagingSource
import com.example.marvelapp.api.MarvelApi
import com.example.marvelapp.data.CharacterResult
import retrofit2.HttpException

private const val STARTING_OFFSET = 0
private const val LOAD_SIZE = 20

class CharacterPagingSource(
    private val marvelApi: MarvelApi,
    private val query: String
) : PagingSource<Int, CharacterResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResult> {
        val position = (params.key ?: STARTING_OFFSET)
        return try {

            val characters  = if (query != "") {
                val response = marvelApi.searchCharacter(query = query, offset = position, limit = params.loadSize)
                val data = response.data
                data.results
            } else {
                val response = marvelApi.getAllCharacters(offset = position, limit = params.loadSize)
                val data = response.data
                data.results
            }

            LoadResult.Page(
                data = characters,
                prevKey = if (position == STARTING_OFFSET) null else position - LOAD_SIZE,
                nextKey = if (characters.isEmpty()) null else position + LOAD_SIZE
            )
        }catch (exception: Exception) {
            Log.i("CHARACTER EXCEPTION", exception.toString())
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}