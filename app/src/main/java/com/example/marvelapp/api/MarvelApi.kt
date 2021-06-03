package com.example.marvelapp.api

import com.example.marvelapp.BuildConfig
import com.example.marvelapp.data.MarvelResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    companion object {
        const val BASE_URL = "https://gateway.marvel.com/v1/public/"
        const val HASH = BuildConfig.MARVEL_HASH
        const val PUBLIC_KEY = BuildConfig.MARVEL_PUBLIC_KEY
    }

    @GET("characters")
    suspend fun getAllHeroes(@Query("offset") offset: Int? = 0, @Query("limit") limit : Int ?= 20): MarvelResponse

}