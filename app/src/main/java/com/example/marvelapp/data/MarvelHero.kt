package com.example.marvelapp.data

data class MarvelHero(
    val id: String,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail,
    val comics: Comics,
    val series: Series,
    val stories: Stories,
    val urls: List<Url>
) {
    data class Thumbnail(val path: String, val extension: String)

    data class Url(val type: String, val url: String)

}