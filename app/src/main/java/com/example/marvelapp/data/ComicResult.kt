package com.example.marvelapp.data

data class ComicResult(
    val id: String,
    val title: String,
    val description: String,
    val thumbnail: ComicThumbnail
) {
    data class ComicThumbnail(val path: String, val extension: String)
}