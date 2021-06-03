package com.example.marvelapp.data

import android.util.Log
import androidx.paging.PagingSource
import com.example.marvelapp.api.MarvelApi
import retrofit2.HttpException

private const val STARTING_OFFSET = 0

class CharacterPagingSource(
    private val marvelApi: MarvelApi
) : PagingSource<Int, MarvelHero>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MarvelHero> {
        val position = (params.key ?: STARTING_OFFSET)
        return try {
            val response = marvelApi.getAllHeroes(position, params.loadSize)
            val data = response.data
            val characters = data.results

            LoadResult.Page(
                data = characters,
                prevKey = if (position == STARTING_OFFSET) null else position - 20,
                nextKey = if (characters.isEmpty()) null else position + 20
            )
        }catch (exception: Exception) {
            Log.i("CHARACTER EXCEPTION", exception.toString())
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}