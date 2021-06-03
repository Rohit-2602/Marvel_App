package com.example.marvelapp.data

data class Series(
    val available: String,
    val collectionURI: String,
    val items: List<SeriesItem>
) {

    data class SeriesItem(val name: String, val resourceURI: String)

}