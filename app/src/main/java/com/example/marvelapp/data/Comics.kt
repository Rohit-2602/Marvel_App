package com.example.marvelapp.data

data class Comics(
    val available: String,
    val collectionURI: String,
    val items: List<ComicListItem>
) {
    data class ComicListItem(val name: String, val resourceURI: String)
}